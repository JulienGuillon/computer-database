package com.excilys.computerdatabase.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.excilys.computerdatabase.validation.EntityValidation;

/**
 * @author Guillon Julien
 *
 * 27 févr. 2017
 */
public class EntityValidationTest {

    /**
     *
     */
    @Test
    public void nameIsValid() {
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("name")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("Name")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("NAME")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("name-test")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("naMe")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("name test")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("name_test")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("    name")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("name   ")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("na   me")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("name123")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("name 123")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("012name")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("012 name")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("012-name")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("012_name")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("na123me")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("n")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("N")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("122-455")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("122_445")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("12 532")));
        assertTrue(EntityValidation.nameIsValid(Optional.ofNullable("Name.test")));

    }

    /**
     *
     */
    @Test
    public void nameIsNotValid() {
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable(" ")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable(null)));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("name*")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("name!")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("name+")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("   +")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("<>")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("name;")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("name,")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("à")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("é")));
        assertFalse(EntityValidation.nameIsValid(Optional.ofNullable("name:test")));


    }
}
