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
import com.moss.identity.Profile;
import com.moss.identity.standard.Password;
import com.moss.identity.tools.IdProover;
import com.moss.identity.tools.IdProovingException;

public class SimpleIdProover implements IdProover {
		private final SimpleId id;
		private final Password confirmation;
		
		public SimpleIdProover(String id, String password){
			this(new SimpleId(id), new Password(password));
		}
		
		public SimpleIdProover(SimpleId id, String password) {
			this(id, new Password(password));
		}
		
		public SimpleIdProover(SimpleId id, Password confirmation) {
			super();
			if(id==null){
				throw new NullPointerException("no id specified");
			}
			this.id = id;
			if(confirmation==null){
				throw new NullPointerException("no password specified");
			}
			this.confirmation = confirmation;
		}
		
		public IdProof giveProof() throws IdProovingException {
			return new SimpleIdProof(id, confirmation.getPassword());
		}
		public Id getIdentity() {
			return id;
		}
		public Profile profile() throws IdProovingException {
			return new SimpleProfile(id) ;
		}
}
