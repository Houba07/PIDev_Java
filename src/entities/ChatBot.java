package entities;

public class ChatBot {

    public String processInput(String input) {
        if(null == input)return "Malheuresement j'ai pas une reponse à ce genre de Message , merci d'attendez nos mise à jour systeme !";
        else switch (input) {
            case "salut":
                return "Bonjour, comment puis-je vous aider ?";
            case "hi":
                return "Bonjour, comment puis-je vous aider ?";
            case "specialites":
                return "-Cardiologie\n" +
                        "-Médecine dentaire\n" +
                        "-Médecine générale\n" +
                        "-Dermatologie\n" +
                        "-Ophtalmologie\n" +
                        "-Nutritioniste\n" +
                        "-Psychiatrie";
            case "hello":
                return "Bonjour, comment puis-je vous aider ?";
            case "bnj":
                return "Bonjour, comment puis-je vous aider ?";
            case "bonjour":
                return "Bonjour, comment puis-je vous aider ?";
            case "pouvez vous m'expliquer le concept de cette plateforme":
                return "DOCARE permet aux patients de consulter un médecin \ninscrit à l’Ordre des médecins, via vidéo depuis  leur \ntablette ou smartphone  a pour objectif de rendre la \nsanté plus équitable et accessible " ;
            case "les services":
                return "-Acheter des produits\n" +
                        "-Consulter un médecin en ligne\n" +
                        "-Obtenir un diganostic médical\n" +
                        "-Recevoir une ordonnance électronique\n" +
                        "-Noter nos services et nos médecins\n" +
                        "-Contact direct entre les patients et les médecins dans Blog\n" +
                        "-Suivi de votre traitement" ;
            case "merci":
                return "A tout moment , je suis là pour vous aidez !";
            case "le concept":
                return "DOCARE permet aux patients de consulter un médecin \ninscrit à l’Ordre des médecins, via vidéo depuis  leur \ntablette ou smartphone  a pour objectif de rendre la \nsanté plus équitable et accessible ";
            default:
                return "Malheuresement j'ai pas une reponse à ce genre de Message , merci d'attendez nos mise à jour systeme !";
        }
    }
}
