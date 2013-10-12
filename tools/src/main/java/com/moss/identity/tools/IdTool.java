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

import java.util.LinkedList;
import java.util.List;

import com.moss.identity.Id;
import com.moss.identity.IdProof;
import com.moss.identity.IdProofRecipie;
import com.moss.identity.IdProofToken;
import com.moss.identity.IdVerifier;

/**
 * <p>
 * The IdTool is the heart of the tooling in this package.  It allows you to
 * perform operations on identities without having to worry about the implementation
 * details of the various types of identity verification systems in use.  This is
 * accomplished by means of IdToolPlugin; the tool selects the appropriate plugin
 * for any given request. It can provide two services based upon this selection:
 * </p>
 * 
 * <ol>
 * <li>
 * It can determine which (if any) IdToolPlugin can be used for authentication 
 * using a particlular set of tokens. If it finds such a IdToolPlugin, it returns
 * an IdProover which may be used to retrieve IdProof based upon those tokens.
 * </li>
 * <li>
 * It can determine which (if any) IdToolPlugin can be used for verifying the
 * validity of a particular IdProof.
 * </li>
 * </ol>
 */
public class IdTool {
	private final List<IdToolPlugin> handlers = new LinkedList<IdToolPlugin>();
	
	public IdTool() {
	}
	
	public IdTool(IdToolPlugin ... plugins){
		for(IdToolPlugin p : plugins){
			addHandler(p);
		}
	}
	public void addHandler(IdToolPlugin handler) {
		handlers.add(handler);
	}
	
	public IdProover getFactory(Id identity, IdProofToken confirmation) throws IdProovingException {
		
		for (IdToolPlugin handler : handlers) {
			if (handler.supports(identity, confirmation)) {
				
				return handler.makeProover(identity, confirmation);
			}
		}
		
		throw new IdProovingException("Cannot find a provider that supports identity " + identity + " and confirmation " + confirmation);
	}
	
	public IdProover getProver(IdProofRecipie r) throws IdProovingException {
		
		for (IdToolPlugin handler : handlers) {
			if (handler.supports(r)) {
				
				return handler.makeProover(r);
			}
		}
		
		throw new IdProovingException("Cannot find a provider that supports " + r);
	}
	
	public IdVerifier getVerifier(IdProof assertion) throws IdProovingException {
		
		for (IdToolPlugin handler : handlers) {
			if (handler.canVerify(assertion)) {
				
				IdVerifier verifier = handler.createVerifier(assertion);
				return verifier;
			}
		}
		
		throw new IdProovingException("Cannot find a provider to verify " + assertion);
	}
}
