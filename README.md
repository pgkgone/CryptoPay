# CryptoPay(WIP)
# Attention! This project is currently in development stage use of it is extremely insecure.

<p align="center">
  <img src="https://user-images.githubusercontent.com/22062599/194790069-784eac1e-4d58-4a40-b03f-57bc23701877.png" alt="CryptoPay"/>
</p>

[Installation and setup](#installation-and-setup)

<h2>About</h2>

This project was started to provide payment service for cases 
when setup professional payment system require more effort then potential income it can bring.

Cryptopay supports main payment blockchains and payment stablecoins. It also allows to easily adding more tokens and chains.

<h3>Currently supported blockchains</h3>

- [x] Binance smart chain
- [x] Etherum
- [x] Tron
- [ ] Solana

<h3>Currently supported tokes</h3>

- [x] USDT
- [x] USDC

<h3>TODO:</h3>

- [ ] Code clenup
- [ ] Optimization
- [ ] Testing
- [ ] DB encrypting
- [ ] Integration samples 
- [ ] Admin panel
- [ ] Example frontend
- [ ] Documentation generation

<h2>Installation and setup</h2>
<h3>Setup</h3>

1. This project working with using https://bscscan.com and https://ethscan.io each require api key to serve requests. 
   Sing up on them and set api key in ```src/main/resources/application-constants.properties```
2. To setup supported blockchains and tokens move in the end of ```databse/migrations/V1__.sql``` and edit insert statements

<h3>Installation</h3>

1. Clone project
2. ```cd ${project.basedir}/databse```
3. ```docker compose up```
4. ```cd ${project.basedir}```
5. ```mvn clean install flyway:migrate spring-boot:run ```
