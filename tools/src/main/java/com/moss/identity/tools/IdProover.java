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
import com.moss.identity.Profile;


/**
 * The IdProover is an encapsulation of a means of proving an ID on demand.  
 * It is intended to be suited for use as the primary handle used by a 
 * running application to refer to its credentials as it operates.  
 * 
 * A secondary (optional) function of an IdProover is to provide access to 
 * metadata (the Profile) assosciated with the ID, if present/appplicable.
 */
public interface IdProover {
	
	/**
	 * Returns the identity this IdProover has been configured to use.
	 * Here so that the IdProover is appropriate for passing around
	 * in an application as the sole container of authentication info.
	 */
	Id getIdentity();

	/**
	 * Return a valid proof. If a previously obtained proof exists, but
	 * it is no loner valid, it must be renewed before being returned. If no
	 * good proof can be returned, it must throw an IdException.
	 */
	IdProof giveProof() throws IdProovingException;
	
	/**
	 * This can be used to return information that relates to the identity
	 * for which this IdProover is configured to authenticate. This returns
	 * null if no profile information can be found.
	 */
	Profile profile() throws IdProovingException;
}
