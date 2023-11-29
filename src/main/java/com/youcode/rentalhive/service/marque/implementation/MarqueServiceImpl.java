package com.youcode.rentalhive.service.marque.implementation;

import com.youcode.rentalhive.handler.exception.OperationException;
import com.youcode.rentalhive.handler.exception.ResourceNotFoundException;
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
        if(marqueRepository.existsByNomMarque(marque.getNomMarque())){
            throw new OperationException("Le nom de la marque fourni existe déjà");
        }
        return marqueRepository.save(marque);
    }

    @Override
    public Marque getMarqueById(Long id) {
        return marqueRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La Marque avec l'id : " + id + " n'existe pas"));
    }

    @Override
    public Marque getMarqueByNom(String nom) {
        return marqueRepository.findByNomMarqueIgnoreCase(nom);
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
            if(!existingMarque.getNomMarque().equals(marque.getNomMarque()) &&
                    marqueRepository.existsByNomMarque(marque.getNomMarque())){
                throw new OperationException("Le nom de la marque fourni existe déjà");
            }
            existingMarque.setNomMarque(marque.getNomMarque());
            existingMarque.setPaysOrigine(marque.getPaysOrigine());
            return marqueRepository.save(existingMarque);
        }else {
            throw new ResourceNotFoundException("La marque avec l'ID : " + id + " n'existe pas.");
        }
    }

    @Override
    public List<Marque> searchMarque(String searchTerm) {
        return marqueRepository.findByNomMarqueStartingWithIgnoreCaseOrPaysOrigineStartingWithIgnoreCase(searchTerm, searchTerm);
    }

    @Override
    public void deleteMarque(Long id) {
        Optional<Marque> existingMarqueOptional = marqueRepository.findById(id);
        if(existingMarqueOptional.isPresent()){
            marqueRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("La marque avec l'ID : " + id + " n'existe pas.");
        }
    }
}
