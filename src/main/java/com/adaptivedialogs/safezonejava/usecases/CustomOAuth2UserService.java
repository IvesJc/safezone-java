//package com.adaptivedialogs.safezonejava.usecases;
//
//import com.adaptivedialogs.safezonejava.entities.Usuario;
//import com.adaptivedialogs.safezonejava.repositories.UsuarioRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    private final UsuarioRepository usuarioRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
//
//        if (oauth2User == null || oauth2User.getAttributes() == null) {
//            throw new OAuth2AuthenticationException("Não foi possível obter os dados do usuário.");
//        }
//
//        Map<String, Object> attributes = oauth2User.getAttributes();
//        System.out.println("Atributos recebidos: " + attributes);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        String email = null;
//        String nome = null;
//
//        switch (registrationId) {
//            case "google":
//                email = (String) attributes.get("email");
//                nome = (String) attributes.get("name");
//                break;
//            case "github":
//                email = (String) attributes.get("email"); // pode vir null
//                nome = (String) attributes.get("name");
//                if (email == null) {
//                    email = attributes.get("login") + "@github.com"; // fallback
//                }
//                if (nome == null) {
//                    nome = (String) attributes.get("login"); // fallback
//                }
//                break;
//            default:
//                throw new OAuth2AuthenticationException("Provedor OAuth2 não suportado: " + registrationId);
//        }
//
//        if (email == null || email.isBlank()) {
//            throw new OAuth2AuthenticationException("Email não encontrado no provedor OAuth2.");
//        }
//
//        Usuario usuario = usuarioRepository.findByEmail(email)
//                .map(existing -> {
//                    existing.setNome(nome);
//                    return usuarioRepository.save(existing);
//                })
//                .orElseGet(() -> {
//                    Usuario novo = new Usuario();
//                    novo.setEmail(email);
//                    novo.setNome(nome);
//                    return usuarioRepository.save(novo);
//                });
//
//        Map<String, Object> customAttributes = new HashMap<>(attributes);
//        customAttributes.put("usuarioId", usuario.getId());
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority("USER")),
//                customAttributes,
//                "name" // esse é o atributo que será usado como identificador principal
//        );
//    }
//}
