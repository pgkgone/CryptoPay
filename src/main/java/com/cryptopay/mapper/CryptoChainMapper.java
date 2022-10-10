package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoChainDto;
import com.cryptopay.model.CryptoChain;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CryptoChainMapper {

    CryptoChain cryptoChainDtoToCryptoChain(CryptoChainDto cryptoChainDto);

    CryptoChainDto cryptoChainToCryptoChainDto(CryptoChain cryptoChain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCryptoChainFromCryptoChainDto(
            CryptoChainDto cryptoChainDto,
            @MappingTarget CryptoChain cryptoChain
    );
}
