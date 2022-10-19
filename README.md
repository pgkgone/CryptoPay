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

<h3>Currently supported tokens</h3>

- [x] USDT
- [x] USDC

<h3>TODO:</h3>

- [ ] Code cleanup
- [ ] Optimization
- [ ] Testing
- [x] Private key encryption in the database
- [ ] Integration samples 
- [ ] Admin panel
- [ ] Example frontend
- [ ] Documentation generation
- [ ] Randomize payment id
- [ ] Captcha support
- [ ] Websocket support
- [x] Wallet creator ip tracking
- [ ] (Optional) User service for reuse of wallets

<h2>Installation and setup</h2>
<h3>Setup</h3>

1. This project working with using https://bscscan.com and https://etherscan.io each requires api key to serve requests. 
   Sing up on them and set api keys in ```src/main/resources/application.yml```
2. To setup supported blockchains and tokens move in the end of ```databse/migrations/V1__.sql``` and edit insert statements or add new version
3. This project uses [Trident java](https://developers.tron.network/reference/quickstart) for tron wallet generation. 
   Project contains precompiled library ```${project.basedir}/trident```. You will probably need to compile the library yourself.

<h3>Installation</h3>

1. Clone project
2. ```cd ${project.basedir}/database```
3. ```docker compose up```
4. ```cd ${project.basedir}```
5. ```mvn clean install flyway:migrate spring-boot:run ```
