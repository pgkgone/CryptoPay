package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoWalletInfoDto;
import com.cryptopay.model.CryptoWallet;
import com.cryptopay.model.Payment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CryptoWalletInfoMapper {

    CryptoWallet cryptoWalletInfoDtoToCryptoWallet(CryptoWalletInfoDto cryptoWalletInfoDto);

    CryptoWalletInfoDto cryptoWalletToCryptoWalletInfoDto(CryptoWallet cryptoWallet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CryptoWallet updateCryptoWalletFromCryptoWalletInfoDto(
            CryptoWalletInfoDto cryptoWalletInfoDto,
            @MappingTarget CryptoWallet cryptoWallet
    );

    @AfterMapping
    default void linkPayment(@MappingTarget CryptoWallet cryptoWallet) {
        Payment payment = cryptoWallet.getPayment();
        if (payment != null) {
            payment.setCryptoWallet(cryptoWallet);
        }
    }
}
