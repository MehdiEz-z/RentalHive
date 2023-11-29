    package com.youcode.rentalhive.service.utilisateur.implemetation;

    import com.youcode.rentalhive.handler.exception.OperationException;
    import com.youcode.rentalhive.handler.exception.ResourceNotFoundException;
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
            if(utilisateurRepository.existsByEmail(utilisateur.getEmail())){
                throw new OperationException("L'email fourni est déjà utilisé");
            }
            return utilisateurRepository.save(utilisateur);
        }

        @Override
        public Utilisateur getUtilisateurById(Long id) {
            return utilisateurRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("L'utilisateur avec l'id : " + id + " n'existe pas"));
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
                if (!existingUtilisateur.getEmail().equals(utilisateur.getEmail()) &&
                        utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
                    throw new OperationException("L'email fourni est déjà utilisé par un autre utilisateur.");
                }
                existingUtilisateur.setNomUtilisateur(utilisateur.getNomUtilisateur());
                existingUtilisateur.setEmail(utilisateur.getEmail());

                return utilisateurRepository.save(existingUtilisateur);
            } else {
                throw new ResourceNotFoundException("L'utilisateur avec l'ID : " + id + " n'existe pas.");
            }
        }

        @Override
        public void deleteUtilisateur(Long id) {
            Optional<Utilisateur> existingUtilisateurOptional = utilisateurRepository.findById(id);

            if (existingUtilisateurOptional.isPresent()) {
                utilisateurRepository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("L'utilisateur avec l'ID : " + id + " n'existe pas.");
            }
        }

    }
