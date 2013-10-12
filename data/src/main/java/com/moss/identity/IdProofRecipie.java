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
package com.moss.identity;

import javax.xml.bind.annotation.XmlElement;

/**
 * Specifies how to provide proof for a given ID.
 * This is useful in situations where party A
 * needs to tell party B how to proove a given ID.
 * e.g. a client assigning an ID to a network service
 * would bundle that ID with one of these so that the 
 * service knows how to make use of the ID as it talks
 * to other services.
 */
public abstract class IdProofRecipie {
	@XmlElement 
	private Id id;
	
	@Deprecated protected IdProofRecipie() {super();}
	
	public IdProofRecipie(Id id) {
		super();
		this.id = id;
	}

	public final Id id() {
		return id;
	}
}
