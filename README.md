# Calculadora Kotlin 🧮

Um aplicativo de calculadora nativo para Android.
## 📱 O que o app faz?
* **Contas básicas:** Faz soma, subtração, multiplicação e divisão.
* **Não trava:** Ele avisa na tela se você tentar fazer uma conta impossível (como dividir por zero), em vez de fechar o aplicativo do nada.
* **Rotação de telar:** A interface se adapta automaticamente se você virar o celular de pé ou deitado, e os números não somem durante o giro!

## 🛠️ Como foi construído?
Para criar essa calculadora, usei as seguintes ferramentas:
* **Kotlin:** A linguagem de programação do projeto.
* **Jetpack Compose:** Para desenhar a tela e os botões de um jeito prático, sem precisar daqueles arquivos XML.
* **ViewModel:** É o "cérebro" do app. Ele guarda os números e o estado da tela, garantindo que nada se perca se você rotacionar o aparelho.
* **Código limpo:** Separei a matemática (as contas em si) da parte visual. Assim, cada arquivo tem sua própria responsabilidade, o que facilita muito na hora de ler e dar manutenção.

## 🚀 Como testar no seu computador
1. Copie o link deste repositório e faça o clone no seu computador (`git clone [COLE-SEU-LINK-AQUI]`).
2. Abra a pasta do projeto usando o **Android Studio**.
3. Espere o programa carregar e configurar os arquivos iniciais.
4. Conecte seu celular ou abra um emulador e clique no botão verde de **Play** lá no topo!

