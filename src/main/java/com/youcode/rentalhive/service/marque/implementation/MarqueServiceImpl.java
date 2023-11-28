package com.youcode.rentalhive.service.marque.implementation;

import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.repository.MarqueRepository;
import com.youcode.rentalhive.service.marque.MarqueService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MarqueServiceImpl implements MarqueService {

    private final MarqueRepository marqueRepository;

    public MarqueServiceImpl(MarqueRepository marqueRepository) {
        this.marqueRepository = marqueRepository;
    }

    @Override
    public Marque createMarque(Marque marque) {
        return marqueRepository.save(marque);
    }

    @Override
    public Optional<Marque> getMarqueById(Long id) {
        return marqueRepository.findById(id);
    }

    @Override
    public List<Marque> getAllMarque() {
        return marqueRepository.findAll();
    }

    @Override
    public Marque updateMarque(Marque marque, Long id) {
        Optional<Marque> existingMarqueOptional = marqueRepository.findById(id);
        if (existingMarqueOptional.isPresent()) {
            Marque existingMarque = existingMarqueOptional.get();
            existingMarque.setNomMarque(marque.getNomMarque());
            existingMarque.setPaysOrigine(marque.getPaysOrigine());
            return marqueRepository.save(existingMarque);
        }
        return null;
    }

    @Override
    public List<Marque> searchMarque(String searchTerm) {
        return marqueRepository.findByNomMarqueStartingWithIgnoreCaseOrPaysOrigineStartingWithIgnoreCase(searchTerm, searchTerm);
    }

    @Override
    public void deleteMarque(Long id) {
        marqueRepository.deleteById(id);
    }
}
