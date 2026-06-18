package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import lab.dto.ComplaintDTO;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String baseUri = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";
        String idZasobu = "153";
        String status = client.target(baseUri + "/" + idZasobu + "/status")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        System.out.println("Status skargi ID " + idZasobu + ": " + status);

        List<ComplaintDTO> allComplaints = client.target(baseUri)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ComplaintDTO>>() {});

        for (ComplaintDTO c : allComplaints) {
            System.out.println("[" + c.getId() + "] Autor: " + c.getAuthor() + ", Tekst: " + c.getComplaintText() + ", Status: " + c.getStatus());
        }

        ComplaintDTO oneComplaint = client.target(baseUri + "/" + idZasobu)
                .request(MediaType.APPLICATION_JSON)
                .get(ComplaintDTO.class);

        System.out.println("Pobrana skarga ID " + idZasobu + " : " + oneComplaint.getComplaintText() + " [" + oneComplaint.getStatus() + "]");

        oneComplaint.setStatus("closed");
        client.target(baseUri + "/" + idZasobu)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(oneComplaint, MediaType.APPLICATION_JSON));
        System.out.println("Żądanie PUT w celu zamknięcia skargi ID: " + idZasobu);

        List<ComplaintDTO> openComplaints = client.target(baseUri)
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ComplaintDTO>>() {});

        System.out.println("\nOTWARTE skargi po modyfikacji");
        for (ComplaintDTO c : openComplaints) {
            System.out.println("[" + c.getId() + "] Autor: " + c.getAuthor() + ", Status: " + c.getStatus());
        }

        client.close();
    }
}