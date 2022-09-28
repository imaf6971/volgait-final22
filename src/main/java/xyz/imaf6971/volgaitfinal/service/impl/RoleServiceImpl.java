package xyz.imaf6971.volgaitfinal.service.impl;

import org.springframework.stereotype.Service;
import xyz.imaf6971.volgaitfinal.exception.RoleNotFoundException;
import xyz.imaf6971.volgaitfinal.model.Role;
import xyz.imaf6971.volgaitfinal.repository.RoleRepository;
import xyz.imaf6971.volgaitfinal.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getDefaultRole() {
        return repository.findByTitle("USER")
                .orElseThrow(() -> new RoleNotFoundException("USER role not found"));
    }
}
