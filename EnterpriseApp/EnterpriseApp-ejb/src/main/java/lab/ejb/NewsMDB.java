package lab.ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@JMSDestinationDefinition(name = "java:app/jms/NewsQueue",
        interfaceName = "jakarta.jms.Queue", resourceAdapter = "jmsra",
        destinationName = "NewsQueue")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/NewsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class NewsMDB implements MessageListener {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        // Zmieniamy na TextMessage
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String content = textMessage.getText();

                if (content != null) {
                    // Dzielimy tekst przy użyciu separatora "|"
                    // Używamy "\\|", ponieważ split przyjmuje Regex, a | to znak specjalny
                    String[] parts = content.split("\\|");

                    if (parts.length >= 2) {
                        String heading = parts[0];
                        String body = parts[1];

                        // Tworzymy nowy obiekt encji NewsItem
                        NewsItem newsItem = new NewsItem();
                        newsItem.setHeading(heading);
                        newsItem.setBody(body);

                        // Utrwalamy w bazie danych
                        em.persist(newsItem);
                    }
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}