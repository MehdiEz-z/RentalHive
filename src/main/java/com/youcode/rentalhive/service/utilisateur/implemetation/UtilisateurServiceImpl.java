    package com.youcode.rentalhive.service.utilisateur.implemetation;

    import com.youcode.rentalhive.model.entity.Utilisateur;
    import com.youcode.rentalhive.repository.UtilisateurRepository;
    import com.youcode.rentalhive.service.utilisateur.UtilisateurService;
    import org.springframework.stereotype.Component;

    import java.util.List;
    import java.util.Optional;

    @Component
    public class UtilisateurServiceImpl implements UtilisateurService {
        private final UtilisateurRepository utilisateurRepository;

        public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
            this.utilisateurRepository = utilisateurRepository;
        }

        @Override
        public Utilisateur createUtilisateur(Utilisateur utilisateur) {
            return utilisateurRepository.save(utilisateur);
        }

        @Override
        public Optional<Utilisateur> getUtilisateurById(Long id) {
            return utilisateurRepository.findById(id);
        }

        @Override
        public List<Utilisateur> getAllUtilisateurs() {
            return utilisateurRepository.findAll();
        }

        @Override
        public List<Utilisateur> searchUtilisateurs(String searchTerm) {
            return utilisateurRepository.findByNomUtilisateurStartingWithIgnoreCaseOrEmailStartingWithIgnoreCase(searchTerm, searchTerm);
        }

        @Override
        public Utilisateur updateUtilisateur(Utilisateur utilisateur, Long id) {
            Optional<Utilisateur> existingUtilisateurOptional = utilisateurRepository.findById(id);
            if (existingUtilisateurOptional.isPresent()) {
                Utilisateur existingUtilisateur = existingUtilisateurOptional.get();
                existingUtilisateur.setNomUtilisateur(utilisateur.getNomUtilisateur());
                existingUtilisateur.setEmail(utilisateur.getEmail());
                return utilisateurRepository.save(existingUtilisateur);
            }
            return null;
        }

        @Override
        public void deleteUtilisateur(Long id) {
            utilisateurRepository.deleteById(id);
        }
    }
