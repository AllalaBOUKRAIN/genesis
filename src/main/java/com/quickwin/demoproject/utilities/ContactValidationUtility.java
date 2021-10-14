package com.quickwin.demoproject.utilities;

import com.quickwin.demoproject.entities.Contact;

public class ContactValidationUtility {

    public static boolean validateContactBeforePersist(Contact contact){
        ContactEntrepriseRelationType relation = contact.getRelationType();
        if(relation == null)
            return false;
        if(relation.equals(ContactEntrepriseRelationType.FREELANCER)){
            if(contact.getNumeroTva() == null || contact.getNumeroTva().equals("")){
                return false;
            }
        }
        return true;
    }
}
