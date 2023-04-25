package entities;

public class ChatBot {

    public String processInput(String input) {
        if(null == input)return "Malheuresement j'ai pas une reponse à ce genre de Message , merci d'attendez nos mise à jour systeme !";
        else switch (input) {
            case "Salut":
                return "Bonjour, comment puis-je vous aider ?";
            case "Bonjour":
                return "Bonjour, comment puis-je vous aider ?";
            case "Pouvez vous m'expliquer le concept de cette plateforme":
                return "DOCARE permet aux patients de consulter un médecin \ninscrit à l’Ordre des médecins, via vidéo depuis  leur \ntablette ou smartphone  a pour objectif de rendre la \nsanté plus équitable et accessible " ;
            case "Les services":
                return "-Acheter des produits\n" +
                        "-Consulter un médecin en ligne\n" +
                        "-Obtenir un diganostic médical\n" +
                        "-Recevoir une ordonnance électronique\n" +
                        "-Noter nos services et nos médecins\n" +
                        "-Contact direct entre les patients et les médecins dans Blog\n" +
                        "-Suivi de votre traitement" ;
            case "Merci":
                return "A tout moment , je suis là pour vous aidez !";
            case "Le concept":
                return "DOCARE permet aux patients de consulter un médecin \ninscrit à l’Ordre des médecins, via vidéo depuis  leur \ntablette ou smartphone  a pour objectif de rendre la \nsanté plus équitable et accessible ";
            default:
                return "Malheuresement j'ai pas une reponse à ce genre de Message , merci d'attendez nos mise à jour systeme !";
        }
    }
}
