# Previsão do Tempo JPR

## 📖 Sobre o Projeto

**Previsão do Tempo JPR** é um aplicativo Android desenvolvido como um projeto acadêmico para a disciplina de Sistemas Para Internet da UNIPAR. O objetivo do aplicativo é fornecer a previsão do tempo para uma cidade, oferecendo múltiplas formas de selecioná-la, e apresentar os dados de forma clara e intuitiva, seguindo os requisitos técnicos da atividade.

O app conta com uma tela principal com abas, um menu de navegação lateral, e telas de suporte como "Sobre" e busca por cidade.

---

## ✨ Funcionalidades Principais

*   **Tela de Abertura (Splash Screen):** Uma tela inicial que é exibida por 3 segundos na inicialização do app.
*   **Navegação por Abas:** A tela principal possui duas abas:
    *   **Previsão:** Exibe a previsão do tempo para os próximos dias em formato de lista, consumindo a API da [HG Brasil](https://console.hgbrasil.com/documentation/weather).
    *   **Mapa:** Mostra um mapa com um marcador fixado na localização da cidade consultada.
*   **Múltiplos Métodos de Seleção de Cidade:**
    *   **Pesquisa por Nome:** Uma tela dedicada permite que o usuário digite o nome de uma cidade para consultar.
    *   **Scanner de QR Code:** Utiliza a câmera para ler um QR Code que contenha o nome de uma cidade.
    *   **Geolocalização Atual:** Um botão obtém a localização atual do dispositivo via GPS e a utiliza para a consulta.
*   **Menu de Navegação:**
    *   **Menu Lateral (Navigation Drawer):** Acessível pelo ícone "hambúrguer" (☰), contém as ações de pesquisa, scan de QR code e geolocalização.
    *   **Menu Superior (Options Menu):** Acessível pelos três pontinhos (⋮), contém a tela "Sobre".
*   **Tela Sobre:** Uma tela que exibe as informações do desenvolvedor do projeto.

---

## 🛠️ Tecnologias e Bibliotecas Utilizadas

*   **Linguagem:** Java
*   **IDE:** Android Studio
*   **Componentes de UI:**
    *   `Material Design Components`
    *   `ViewPager2` e `TabLayout` para a navegação por abas.
    *   `DrawerLayout` e `NavigationView` para o menu lateral.
    *   `RecyclerView` e `CardView` para a lista de previsão do tempo.
*   **APIs e Serviços Externos:**
    *   **Volley:** Para realizar as requisições de rede à API de previsão do tempo.
    *   **Google Maps SDK:** Para a exibição do mapa.
    *   **Google Play Services Location:** Para obter a geolocalização do dispositivo.
    *   **ZXing (via journeyapps):** Para a funcionalidade de escaneamento de QR Code.

---

## 👨‍💻 Autor

*   **Nome:** João Pedro Raldi
*   **RA:** 09048486
*   **Curso:** Sistemas Para Internet - UNIPAR
