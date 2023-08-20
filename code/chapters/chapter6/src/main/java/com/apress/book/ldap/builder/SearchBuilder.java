package com.apress.book.ldap.builder;

import com.apress.book.ldap.domain.Patron;
import com.apress.book.ldap.mapper.PatronContextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component("searchFilter")
public class SearchBuilder {
    private static final Logger logger = LoggerFactory.getLogger(com.apress.book.ldap.filter.SearchFilter.class);

    @Autowired
    @Qualifier("ldapTemplate")
    private LdapTemplate ldapTemplate;

    public List<Patron> getPatronByLastNameAndPostalCode(String lastName, String postalCode) {

        LdapQuery query = query()
                .countLimit(10)
                .base(LdapUtils.emptyLdapName())
                .where("objectclass").is("person")
                .and("sn").is(lastName)
                .and("postalCode").is(postalCode)
                .and("uid").isPresent();

        return ldapTemplate.search(query, new PatronContextMapper());
    }
}
