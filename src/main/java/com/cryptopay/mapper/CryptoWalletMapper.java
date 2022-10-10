package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoWalletDto;
import com.cryptopay.model.CryptoWallet;
import com.cryptopay.service.chainclients.walletgenerator.GeneratedWalletInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CryptoWalletMapper {

    CryptoWallet cryptoWalletDtoToCryptoWallet(CryptoWalletDto cryptoWalletDto);

    CryptoWalletDto cryptoWalletToCryptoWalletDto(CryptoWallet cryptoWallet);

    CryptoWallet generatedWalletToCryptoWallet(GeneratedWalletInfo generatedWalletInfo);

    GeneratedWalletInfo cryptoWalletToGeneratedWalletInfo(CryptoWallet generatedWalletInfo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CryptoWallet updateCryptoWalletFromCryptoWalletDto(
            CryptoWalletDto cryptoWalletDto,
            @MappingTarget CryptoWallet cryptoWallet
    );
}
