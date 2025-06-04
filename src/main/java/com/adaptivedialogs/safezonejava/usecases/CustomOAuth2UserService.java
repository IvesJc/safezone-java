package com.adaptivedialogs.safezonejava.usecases;

import com.adaptivedialogs.safezonejava.entities.Usuario;
import com.adaptivedialogs.safezonejava.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UsuarioRepository usuarioRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String nome = oauth2User.getAttribute("name"); // pode variar conforme o provedor

        if (email == null) {
            throw new OAuth2AuthenticationException("Email não encontrado no provedor OAuth2.");
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .map(existing -> {
                    existing.setNome(nome);
                    return usuarioRepository.save(existing);
                })
                .orElseGet(() -> {
                    Usuario novo = new Usuario();
                    novo.setEmail(email);
                    novo.setNome(nome);
                    return usuarioRepository.save(novo);
                });

        Map<String, Object> atributos = new HashMap<>(oauth2User.getAttributes());
        atributos.put("usuarioId", usuario.getId());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                atributos,
                "name" // nome do atributo que representa o identificador (muda por provedor)
        );
    }
}
