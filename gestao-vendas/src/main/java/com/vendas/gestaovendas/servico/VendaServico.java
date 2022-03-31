package com.vendas.gestaovendas.servico;

import com.vendas.gestaovendas.DTO.Venda.ClienteVendaResponseDTO;
import com.vendas.gestaovendas.DTO.Venda.ItemVendaResponseDTO;
import com.vendas.gestaovendas.DTO.Venda.VendaRequestDTO;
import com.vendas.gestaovendas.DTO.Venda.VendaResponseDTO;
import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.entidades.ItemVenda;
import com.vendas.gestaovendas.entidades.Venda;
import com.vendas.gestaovendas.entidades.Vendedor;
import com.vendas.gestaovendas.excecao.RegraDeNegocioException;
import com.vendas.gestaovendas.repositorio.ItemVendaRepositorio;
import com.vendas.gestaovendas.repositorio.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServico extends abstractVendaServico{

    private ClienteServico clienteServico;
    private VendaRepositorio vendaRepositorio;
    private ItemVendaRepositorio itemVendaRepositorio;

    @Autowired
    private VendedorServico vendedorServico;

    @Autowired
    public VendaServico(ClienteServico clienteServico, VendaRepositorio vendaRepositorio, ItemVendaRepositorio itemVendaRepositorio) {
        this.clienteServico = clienteServico;
        this.vendaRepositorio = vendaRepositorio;
        this.itemVendaRepositorio = itemVendaRepositorio;
    }

    public List<VendaResponseDTO> listarTodos(){
        return vendaRepositorio.findAll().stream().map(this::criandoVendaResponseDTO)
                .collect(Collectors.toList());
    }

    public ClienteVendaResponseDTO listarPorCodigoCliente (Long codigoCliente){
        Cliente cliente =  validarClienteExiste(codigoCliente);
        List<VendaResponseDTO> vendaResponseDTOList =  vendaRepositorio.findByClienteCodigo(codigoCliente).stream()
                .map(this::criandoVendaResponseDTO).collect(Collectors.toList());
        return new ClienteVendaResponseDTO(cliente.getNome(),vendaResponseDTOList);
    }


    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda){
       Venda venda = validarVendaExite(codigoVenda);
       return new ClienteVendaResponseDTO(venda.getCliente().getNome(),
               Arrays.asList(criandoVendaResponseDTO(venda)));
    }
    /*
    public ClienteVendaResponseDTO salvar (VendaRequestDTO vendadto){

    }

     */

    private Venda validarVendaExite(Long codigoVenda) {
        Optional<Venda> venda = vendaRepositorio.findById(codigoVenda);
        if (venda.isEmpty()){
            throw new RegraDeNegocioException(String.format("O codigo da Venda informado ( % ) não existe no Banco", codigoVenda));
        }
        return venda.get();
    }

    private Cliente validarClienteExiste(Long codigoCliente){
        Optional<Cliente> cliente = clienteServico.buscarPorCodigo(codigoCliente);
        if(cliente.isEmpty()){
            throw new RegraDeNegocioException(String.format("O cliente de codigo % não existe", codigoCliente));
        }
        return cliente.get();
    }

    private Vendedor validarVendedorExiste(Long codigoVendedor) {
        Optional<Vendedor> vendedor = vendedorServico.buscarPorCodigo(codigoVendedor);

        if (vendedor.isEmpty()) {
            throw new RegraDeNegocioException(String.format("O vendedor de codigo %s não existe", codigoVendedor));
        }

        return vendedor.get();
    }
    // colocar em uma classe abstrata para ficar organizado
    // colocar em uma classe abstrata para ficar organizado
    private VendaResponseDTO criandoVendaResponseDTO(Venda venda){
           List<ItemVendaResponseDTO> itemVendaResponseDTO =  itemVendaRepositorio.findByVendaCodigo(venda.getCodigo()).stream().
                   map(this::criandoItemVendaResponseDTO).collect(Collectors.toList());
           return new VendaResponseDTO(venda.getCodigo(), venda.getData(), venda.getAtivo(), itemVendaResponseDTO, venda.getCliente(), venda.getVendedor());
    }

    // colocar em uma classe abstrata para ficar organizado
    private ItemVendaResponseDTO criandoItemVendaResponseDTO(ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuanidade(),
                itemVenda.getPrecoVendido(), itemVenda.getProdutos().getCodigo(), itemVenda.getProdutos().getDescricao());
    }
}
