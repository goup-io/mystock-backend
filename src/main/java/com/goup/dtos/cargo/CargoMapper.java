package com.goup.dtos.cargo;

import com.goup.entities.cargos.Cargo;

import java.util.List;
import java.util.Objects;

public class CargoMapper {
    public static Cargo toEntity(CargoCadastrarDto cargoCadastrarDto){
        Cargo cargo = new Cargo();
        cargo.setNome(cargoCadastrarDto.nome());
        cargo.setDescricao(cargoCadastrarDto.descricao());
        return cargo;
    }

    public static CargoResponseDto responseToEntity(Cargo cargoResponse){
        if (Objects.isNull(cargoResponse)) {
            return null;
        }

        return new CargoResponseDto(cargoResponse.getId(), cargoResponse.getNome(), cargoResponse.getDescricao());
    }

    public static List<CargoResponseDto> toDto(List<Cargo> cargos) {
        return cargos.stream()
                .map(CargoMapper::responseToEntity)
                .toList();
    }
}
