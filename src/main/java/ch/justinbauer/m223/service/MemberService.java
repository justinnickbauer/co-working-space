package ch.justinbauer.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Validator;

import ch.justinbauer.m223.model.Member;

@ApplicationScoped
public class MemberService {
    @Inject
    EntityManager entityManager;

    @Inject
    Validator validator; 

    @Transactional
    public void deleteMember(Long id) {
        Member member = entityManager.find(Member.class, id);
        entityManager.remove(member);
    }

    @Transactional
    public Member updateMember(Long id, Member member) {
        member.setId(id);
        return entityManager.merge(member);
    }

    public List<Member> findAll() {
        var query = entityManager.createQuery("FROM MEMBER", Member.class);
        return query.getResultList();
    }

    public Optional<Member> findByEmail(String email) {
        return entityManager
                .createNamedQuery("Member.findByEmail", Member.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    public Optional<Member> findById(Long id) {
        return entityManager
                .createNamedQuery("Member.findById", Member.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }
}
