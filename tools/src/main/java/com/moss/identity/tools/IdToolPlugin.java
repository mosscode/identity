/**
 * Copyright (C) 2013, Moss Computing Inc.
 *
 * This file is part of identity.
 *
 * identity is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * identity is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with identity; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */
package com.moss.identity.tools;

import com.moss.identity.Id;
import com.moss.identity.IdProof;
import com.moss.identity.IdProofRecipie;
import com.moss.identity.IdProofToken;
import com.moss.identity.IdVerifier;

/**
 * Handles all interactions with a given authentication provider.
 */
public interface IdToolPlugin<I extends Id, C extends IdProofToken, A extends IdProof> {

	/**
	 * Returns true if this provider supports authentication using the
	 * supplied identity and token.
	 * 
	 * This does not neccesarily mean it knows the details of how to perform 
	 * the authentication (provider url's, etc). The ability to determine 
	 * that information from just an Identity and a Confirmation object is 
	 * implementation dependent. 
	 */
	boolean supports(Id identity, IdProofToken token);
	
	/**
	 * Returns true if this plugin can produce an IdProover
	 * for the specified recipie
	 */
	boolean supports(IdProofRecipie recipie);
	
	/**
	 * Returns an IdProver for the specified recipie
	 */
	IdProover makeProover(IdProofRecipie recipie) throws IdProovingException;
	
	
	/**
	 * Returns an identity factory which can be used to authenticate with this
	 * provider. 
	 */
	IdProover makeProover(I identity, C confirmation) throws IdProovingException;
	
	/**
	 * Returns true if this provider supports verification of the supplied
	 * assertion. 
	 */
	boolean canVerify(IdProof assertion);
	
	/**
	 * Returns an assertion verifier which can be used to verify assertions
	 * issued by this provider.
	 */
	IdVerifier createVerifier(A proof) throws IdProovingException ;
}
