package lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ModelMapperProducer {
    @Produces
    @ApplicationScoped
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
