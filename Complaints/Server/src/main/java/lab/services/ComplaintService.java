package lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import lab.data.ComplaintRepository;
import lab.dto.ComplaintDTO;
import lab.entities.Complaint;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.util.List;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

@ApplicationScoped
public class ComplaintService {
    @Inject
    ComplaintRepository repository;
    @Inject
    ModelMapper mapper;

    @Transactional
    public void create(ComplaintDTO dto) {
        repository.create(mapper.map(dto, Complaint.class));
    }
    @Transactional
    public void edit(ComplaintDTO dto) {
        repository.edit(mapper.map(dto, Complaint.class));
    }
    @Transactional
    public ComplaintDTO find(Long id) {
        Complaint complaint = repository.find(id);
        ComplaintDTO dto = mapper.map(complaint, ComplaintDTO.class);
        return dto;
    }
    @Transactional
    public void remove(ComplaintDTO dto) {
        repository.remove(mapper.map(dto, Complaint.class));
    }

    @Transactional
    public List<ComplaintDTO> findAll(String status) {

        // Przekazujemy status do repozytorium i mapujemy encje na DTO
        return repository.findAll(status).stream()
                .map(complaint -> mapper.map(complaint, ComplaintDTO.class))
                .collect(Collectors.toList());
    }


}
