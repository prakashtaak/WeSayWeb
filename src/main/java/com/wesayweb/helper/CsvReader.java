package com.wesayweb.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wesayweb.model.Badges;
import com.wesayweb.model.SettingsCategory;
import com.wesayweb.model.Traits;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvReader {

	public static List<Traits> getTraits() {
		List<Traits> returnList = new ArrayList<Traits>();
		ClassLoader classLoader = CsvReader.class.getClassLoader();
		File file = new File(classLoader.getResource("supported_stuff/traitlist.csv").getFile());
		BufferedReader br = null;
		int linecounter = 0;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if (linecounter > 0) {

					Traits traitObj = new Traits();
					String[] trait = line.split(cvsSplitBy);
					traitObj.setIsdefault(Integer.valueOf(trait[3]));
					traitObj.setTraitname(trait[0].trim());
					if (trait[1].trim().equalsIgnoreCase("y")) {
						traitObj.setActivestatus(1);
					} else {
						traitObj.setActivestatus(0);
					}
					traitObj.setTraittype(trait[2].trim().toLowerCase());
					traitObj.setTraitcreatedby(Long.valueOf(0));
					traitObj.setTraitidentifier(10000);
					traitObj.setApproveddate(new Date());
					traitObj.setTraituniqueid(WesayStringUtil.generateRandomNumber());

					returnList.add(traitObj);

				}
				linecounter++;
			}

		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		return returnList;
	}

	public static List<SettingsCategory> getSettings() {
		List<SettingsCategory> returnList = new ArrayList<SettingsCategory>();
		ClassLoader classLoader = CsvReader.class.getClassLoader();
		File file = new File(classLoader.getResource("supported_stuff/user_settings.csv").getFile());
		BufferedReader br = null;
		int linecounter = 0;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if (linecounter > 0) {
					SettingsCategory categoryObj = new SettingsCategory();
					String[] rows = line.split(cvsSplitBy);
					categoryObj.setCategoryname(rows[0].trim());
					categoryObj.setCategorydescription(rows[1].trim());
					categoryObj.setAllowedmultiplevalue(Integer.valueOf(rows[2].trim()));
					categoryObj.setDefaultvalue(Integer.valueOf(rows[3].trim()));
					categoryObj.setUniqueid(rows[4].trim());
					returnList.add(categoryObj);
				}
				linecounter++;
			}

		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		return returnList;
	}
	
	public static List<Badges> getBadges() {
		List<Badges> returnList = new ArrayList<Badges>();
		ClassLoader classLoader = CsvReader.class.getClassLoader();

		List<String> badgeCategories = readBadgesCategoryData();
		badgeCategories.forEach( x -> {

			File file = new File(classLoader.getResource("supported_stuff/"+x+".csv").getFile());
			BufferedReader br = null;
			int linecounter = 0;
			String line = "";
			String cvsSplitBy = ",";
			try {

				br = new BufferedReader(new FileReader(file));
				while ((line = br.readLine()) != null) {
					if (linecounter > 0) {
						Badges badgeObj = Badges.builder().build();
						String[] rows = line.split(cvsSplitBy);
						badgeObj.setBadgename(rows[0].trim());
						badgeObj.setBadgeisactive(Integer.valueOf(rows[1].trim()));

						returnList.add(badgeObj);
					}
					linecounter++;
				}

			} catch (FileNotFoundException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						log.error(e.getMessage());
					}
				}
			}
		});

		return returnList;
	}

	public static List<String> readBadgesCategoryData(){
		ClassLoader classLoader = CsvReader.class.getClassLoader();
		File masterFile= new File(classLoader.getResource("supported_stuff/badge_master.csv").getFile());
		BufferedReader br = null;
		int linecounter = 0;
		String line = "";
		String cvsSplitBy = ",";
		List<String> categories=new ArrayList<>();
		try {

			br = new BufferedReader(new FileReader(masterFile));
			while ((line = br.readLine()) != null) {
				if (linecounter > 0) {
					String[] rows = line.split(cvsSplitBy);
					categories.add(rows[0].trim());
				}
				linecounter++;
			}

		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
		return categories;
	}

	public static void main(String[] args) {
		readBadgesCategoryData();
	}
}