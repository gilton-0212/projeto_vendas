package com.vendas.gestaovendas.servico;

import com.vendas.gestaovendas.DTO.Venda.*;
import com.vendas.gestaovendas.entidades.*;
import com.vendas.gestaovendas.excecao.RegraDeNegocioException;
import com.vendas.gestaovendas.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServico extends abstractVendaServico{

    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private ProdutoServico produtoServico;

    @Autowired
    private VendaRepositorio vendaRepositorio;

    @Autowired
    private VendedorRepositorio vendedorRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private ItemVendaRepositorio itemVendaRepositorio;

    public List<ItemVenda> ListarTodosItens(){
        return itemVendaRepositorio.findAll();
    }

    
    public ClienteVendaResponseDTO listaVendaPorCliente(Long codigoCliente) {
        Cliente cliente = validarClienteVendaExiste(codigoCliente);
        List<VendaResponseDTO> vendaResponseDtoList = vendaRepositorio.findByClienteCodigo(codigoCliente).stream()
                .map(venda -> criandoVendaResponseDTO(venda, itemVendaRepositorio.findByVendaCodigo(venda.getCodigo())))
                .collect(Collectors.toList());
        return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDtoList);
    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda) {
        Venda venda = validarVendaExiste(codigoVenda);
        List<ItemVenda> itensVendaList = itemVendaRepositorio.findByVendaCodigo(venda.getCodigo());
        return retornandoClienteVendaResponseDTO(venda, itensVendaList);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public ClienteVendaResponseDTO salvar(VendaRequestDTO vendaDTO){

        //var cliente = clienteRepositorio.findById(vendaDTO.getCodigoCliente());
        //var vendedor = vendedorRepositorio.findById(vendaDTO.getCodigoVendedor());

        Cliente cliente = validarClienteVendaExiste(vendaDTO.getCodigoCliente());
        Vendedor vendedor = validarVendedorVendaExiste(vendaDTO.getCodigoVendedor());
        validarProdutoExisteEAtualizarQuantidade(vendaDTO.getItensVendaDto());
        var vendaSalva = salvarVenda(vendaDTO, cliente, vendedor);

        return retornandoClienteVendaResponseDTO(vendaSalva);
    }

    public ClienteVendaResponseDTO atualizar(Long codigoVenda, VendaRequestDTO vendaDto) {
        var vendaSalva = validarVendaExiste(codigoVenda);
        Cliente cliente = validarClienteVendaExiste(vendaDto.getCodigoCliente());
        Vendedor vendedor = validarVendedorVendaExiste(vendaDto.getCodigoVendedor());

        devolverEstoque(vendaSalva.getItens());
        validarProdutoExisteEAtualizarQuantidade(vendaDto.getItensVendaDto());
        Venda vendaAtualizada = atualizarVenda(codigoVenda, vendaDto, cliente, vendedor);
        return retornandoClienteVendaResponseDTO(vendaAtualizada);

    }

    public void deletar(Long codigoVenda) {
        validarVendaExiste(codigoVenda);
        List<ItemVenda> itensVenda = itemVendaRepositorio.findByVendaCodigo(codigoVenda);
        devolverEstoque(itensVenda);
        itemVendaRepositorio.deleteAll(itensVenda);
        vendaRepositorio.deleteById(codigoVenda);
    }


    public Venda salvarVenda(VendaRequestDTO vendaDto, Cliente cliente, Vendedor vendedor) {

        var novaVenda = new Venda();
        novaVenda.setVendedor(new Vendedor(vendedor.getCodigo()));
        novaVenda.setCliente(new Cliente(cliente.getCodigo()));
        novaVenda.setData(vendaDto.getData());
        novaVenda.setAtivo(vendaDto.getAtivo());
        novaVenda.setItens(new ArrayList<>());

        vendaDto.getItensVendaDto().forEach(item->{
            var novoItem = new ItemVenda();
            novoItem.setVenda(novaVenda);
            novoItem.setQuantidade(item.getQuantidade());
            novoItem.setPrecoVendido(item.getPrecoVendido());
            novoItem.setProdutos(new Produtos(item.getCodigoProduto()));
            novaVenda.getItens().add(novoItem);
        });

        vendaRepositorio.save(novaVenda);

        return novaVenda;
    }

    public Venda atualizarVenda(Long codigoVenda,VendaRequestDTO vendaDto, Cliente cliente, Vendedor vendedor) {
        var novaVenda = new Venda();
        novaVenda.setCodigo(codigoVenda);
        novaVenda.setVendedor(new Vendedor(vendedor.getCodigo()));
        novaVenda.setCliente(new Cliente(cliente.getCodigo()));
        novaVenda.setData(vendaDto.getData());
        novaVenda.setAtivo(vendaDto.getAtivo());
        novaVenda.setItens(new ArrayList<>());

        vendaDto.getItensVendaDto().forEach(item->{
            var novoItem = new ItemVenda();
            novoItem.setVenda(novaVenda);
            novoItem.setQuantidade(item.getQuantidade());
            novoItem.setPrecoVendido(item.getPrecoVendido());
            novoItem.setProdutos(new Produtos(item.getCodigoProduto()));
            novaVenda.getItens().add(novoItem);
        });

        vendaRepositorio.save(novaVenda);

        return novaVenda;
    }


    private void validarProdutoExisteEAtualizarQuantidade(List<ItemVendaRequestDTO> itensVendaDto) {
        itensVendaDto.forEach(item -> {
            Produtos produto = produtoServico.validarProdutoExiste(item.getCodigoProduto());
            validarQuantidadeProdutoExiste(produto, item.getQuantidade());
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoServico.atualizarQuantidade(produto);
        });

    }

    private void validarQuantidadeProdutoExiste(Produtos produto, Integer quantidade) {
        if(!(produto.getQuantidade() >= quantidade)){
            throw new RegraDeNegocioException(String.format("A quantidade %s informada do produto %s não está disponivel no estoque.",
                    quantidade, produto.getDescricao()));
        }

    }

    private void devolverEstoque(List<ItemVenda> itensVenda) {
        itensVenda.forEach(item ->
        {
            Produtos produto = produtoServico.validarProdutoExiste(item.getProdutos().getCodigo());
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            produtoServico.atualizarQuantidade(produto);
        });
    }

    private Cliente validarClienteVendaExiste(Long codigoCliente) {
        Optional<Cliente> cliente = clienteServico.buscarPorCodigo(codigoCliente);
        if (cliente.isEmpty()) {
            throw new RegraDeNegocioException(
                    String.format("O Cliente de código %s informado não existe no cadastro.", codigoCliente));
        }
        return cliente.get();
    }


    private Venda validarVendaExiste(Long codigoVenda) {
        Optional<Venda> venda = vendaRepositorio.findById(codigoVenda);
        if (venda.isEmpty()) {
            throw new RegraDeNegocioException(String.format("Venda de código %s não encontrada.", codigoVenda));
        }
        return venda.get();
    }

    private Vendedor validarVendedorVendaExiste(Long codigoVendedor) {
        Optional<Vendedor> vendedor = vendedorRepositorio.findById(codigoVendedor);
        if (vendedor.isEmpty()) {
            throw new RegraDeNegocioException(String.format("O vendedor de codigo %s não existe", codigoVendedor));
        }
        return vendedor.get();
    }

////////////////////////////////////////////////
    //
    //colocar na classe abstrata
    private VendaResponseDTO criandoVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList) {
        List<ItemVendaResponseDTO> itensVendaResponseDto = itensVendaList.stream()
                .map(this::criandoItensVendaResponseDto).collect(Collectors.toList());
        return new VendaResponseDTO(venda.getCodigo(), venda.getData(), venda.getAtivo() ,itensVendaResponseDto,
                venda.getVendedor().getNome(), venda.getCliente().getNome());
    }

    private ItemVendaResponseDTO criandoItensVendaResponseDto(ItemVenda itemVenda) {
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
                itemVenda.getProdutos().getCodigo(), itemVenda.getProdutos().getDescricao());
    }

    private ClienteVendaResponseDTO retornandoClienteVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList) {
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(
                criandoVendaResponseDTO(venda, itensVendaList)));
    }

    protected VendaResponseDTO criandoVendaResponseDTO(Venda venda) {
        List<ItemVendaResponseDTO> itensVendaResponseDTO = venda.getItens()
                .stream().map(this::criandoItemVendaResponseDTO).collect(Collectors.toList());

        return new VendaResponseDTO(venda.getCodigo(), venda.getData(),venda.getAtivo(),
                itensVendaResponseDTO, venda.getVendedor().getNome(), venda.getCliente().getNome());
    }

    protected ItemVendaResponseDTO criandoItemVendaResponseDTO(ItemVenda itemVenda) {
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
                itemVenda.getProdutos().getCodigo(),
                itemVenda.getProdutos().getDescricao());
    }

    protected ClienteVendaResponseDTO retornandoClienteVendaResponseDTO(Venda venda) {
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(),List.of(
                criandoVendaResponseDTO(venda)));
    }
}