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
package com.moss.identity.simple;

import com.moss.identity.Id;
import com.moss.identity.IdProof;
import com.moss.identity.IdProofRecipie;
import com.moss.identity.IdProofToken;
import com.moss.identity.IdVerifier;
import com.moss.identity.Profile;
import com.moss.identity.standard.Password;
import com.moss.identity.standard.PasswordProofRecipie;
import com.moss.identity.tools.IdProover;
import com.moss.identity.tools.IdProovingException;
import com.moss.identity.tools.IdToolPlugin;

public class SimpleIdIdToolPlugin implements IdToolPlugin<SimpleId, Password, SimpleIdProof> {
	
	private PasswordChecker passwordChecker;
	
	public SimpleIdIdToolPlugin(PasswordChecker passwordChecker) {
		super();
		this.passwordChecker = passwordChecker;
	}

	public boolean canVerify(IdProof a) {
		return a instanceof SimpleIdProof;
	}
	
	public static Profile createProfile(final SimpleId id){
		return new SimpleProfile(id);
	}
	
	public IdProover makeProover(SimpleId identity, Password confirmation) {
		return new SimpleIdProover(identity, confirmation);
	}
	
	public IdVerifier createVerifier(final SimpleIdProof creds) {
		return new IdVerifier() {
			public Id id() throws IdProovingException {
				return creds.id();
			}
			public Profile getProfile() throws IdProovingException {
				return createProfile(creds.id());
			}
			public boolean verify() throws IdProovingException {
				return passwordChecker.matches(creds.id().toString(), creds.password());
			}
		};
	}
	public boolean supports(Id id, IdProofToken conf) {
		return id instanceof SimpleId && conf instanceof Password;
	}
	
	public boolean supports(IdProofRecipie recipie) {
		return recipie instanceof PasswordProofRecipie && recipie.id() instanceof SimpleId;
	}
	
	public IdProover makeProover(IdProofRecipie recipie) throws IdProovingException {
		PasswordProofRecipie r = (PasswordProofRecipie) recipie;
		return makeProover((SimpleId)r.id(), r.password());
	}
}
