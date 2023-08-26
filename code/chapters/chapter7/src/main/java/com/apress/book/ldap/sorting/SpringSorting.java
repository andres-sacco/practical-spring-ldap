package com.apress.book.ldap.sorting;

import java.util.List;

import javax.naming.directory.SearchControls;

import com.apress.book.ldap.control.SortMultipleControlDirContextProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.control.SortControlDirContextProcessor;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DirContextProcessor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Component;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component("sorting")
public class SpringSorting {

    private static final Logger logger = LoggerFactory.getLogger(SpringSorting.class);

    @Autowired
    @Qualifier("ldapTemplate")
    private LdapTemplate ldapTemplate;

    public List<String> sortByLocation() {

        String[] locationAttributes = { "st", "l" };
        SortMultipleControlDirContextProcessor smcdcp = new SortMultipleControlDirContextProcessor(locationAttributes);

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        EqualsFilter equalsFilter = new EqualsFilter("objectClass", "inetOrgPerson");

        @SuppressWarnings("unchecked")
        AbstractContextMapper<String> locationMapper = new AbstractContextMapper<String>() {
            @Override
            protected String doMapFromContext(DirContextOperations context) {
                return context.getStringAttribute("st") + "," + context.getStringAttribute("l");
            }
        };

        List<String> results = ldapTemplate.search("", equalsFilter.encode(), searchControls, locationMapper, smcdcp);

        for (String r : results) {
            logger.info(r);
        }
        return results;
    }

    public List<String> sortByLastName() {

        DirContextProcessor scdcp = new SortControlDirContextProcessor("sn");

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        EqualsFilter equalsFilter = new EqualsFilter("objectClass", "inetOrgPerson");

        @SuppressWarnings("unchecked")
        AbstractContextMapper<String> lastNameMapper = new AbstractContextMapper<String>() {
            @Override
            protected String doMapFromContext(DirContextOperations context) {
                return context.getStringAttribute("sn");
            }
        };

        List<String> lastNames = ldapTemplate.search("", equalsFilter.encode(), searchControls, lastNameMapper, scdcp);

        for (String ln : lastNames) {
            logger.info(ln);
        }
        return lastNames;
    }
}