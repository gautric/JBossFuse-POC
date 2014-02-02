/**
 * Copyright (c) Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.poc.util;

import java.util.Random;

/**
 * Helper class to generate random some data
 */
public class Referentiel {

	private static final String[] NAMES = new String[] { "Paul", "Marie",
			"Akiko", "Yassine", "Julio" };

	private static final String[] SYSTEMS = new String[] { "Interne",
			"Externe", "N/A" };

	private static final String[] GEO = new String[] { "BRA", "CAN", "DNK",
			"FRA", "JPN", "MAR", "RUS" };

	private static final String[] VERSION = new String[] { "1.0", "1.1", "2.0" };

	private static final Random RANDOM = new Random();

	/**
	 * Generate a random name.
	 */
	public static final String randomName() {
		return NAMES[RANDOM.nextInt(NAMES.length)];
	}

	/**
	 * Generate a random name.
	 */
	public static final String randomSystem() {
		return SYSTEMS[RANDOM.nextInt(SYSTEMS.length)];
	}

	/**
	 * Generate a random name.
	 */
	public static final String randomISO3166_1alpha_3() {
		return GEO[RANDOM.nextInt(GEO.length)];
	}

	/**
	 * Generate a random name.
	 */
	public static final String randomVersion() {
		return VERSION[RANDOM.nextInt(VERSION.length)];
	}

}
