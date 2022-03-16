package com.vendas.gestaovendas.mappers;


import com.vendas.gestaovendas.entidades.Categoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    /*CategoriaDTO toModel(Categoria categoria);

    @InheritConfiguration
    Categoria toDomainObject(CategoriaDTO categoriaDTO);

    @Mappings({@Mapping(target = "codigo",ignore = true)})
    void copyToDomainObject(CategoriaDTO categoriaDTO, @MappingTarget Categoria categoria);

     */
}
