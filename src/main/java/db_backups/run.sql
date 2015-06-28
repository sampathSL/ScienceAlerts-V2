-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.16


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sciencealerts
--

CREATE DATABASE IF NOT EXISTS sciencealerts;
USE sciencealerts;

--
-- Definition of table `appcomments`
--

DROP TABLE IF EXISTS `appcomments`;
CREATE TABLE `appcomments` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `SenderId` bigint(20) DEFAULT NULL,
  `COMMENT` varchar(500) DEFAULT NULL,
  `IsChecked` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`Id`),
  KEY `fk_SenderIdAppComments` (`SenderId`),
  KEY `FK4D1A715C2E1FE65` (`SenderId`),
  KEY `FK4D1A715EDB648D0` (`SenderId`),
  CONSTRAINT `FK4D1A715EDB648D0` FOREIGN KEY (`SenderId`) REFERENCES `smssender` (`Id`),
  CONSTRAINT `FK4D1A715C2E1FE65` FOREIGN KEY (`SenderId`) REFERENCES `smssender` (`Id`),
  CONSTRAINT `fk_SenderIdAppComments` FOREIGN KEY (`SenderId`) REFERENCES `smssender` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appcomments`
--

/*!40000 ALTER TABLE `appcomments` DISABLE KEYS */;
/*!40000 ALTER TABLE `appcomments` ENABLE KEYS */;


--
-- Definition of table `help`
--

DROP TABLE IF EXISTS `help`;
CREATE TABLE `help` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `HelpText` varchar(160) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `help`
--

/*!40000 ALTER TABLE `help` DISABLE KEYS */;
/*!40000 ALTER TABLE `help` ENABLE KEYS */;


--
-- Definition of table `incomesmstransaction`
--

DROP TABLE IF EXISTS `incomesmstransaction`;
CREATE TABLE `incomesmstransaction` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `SenderAddress` varchar(100) DEFAULT NULL,
  `SenderSms` varchar(160) DEFAULT NULL,
  `TransactionTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=664 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `incomesmstransaction`
--

/*!40000 ALTER TABLE `incomesmstransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `incomesmstransaction` ENABLE KEYS */;


--
-- Definition of table `sciencealerts`
--

DROP TABLE IF EXISTS `sciencealerts`;
CREATE TABLE `sciencealerts` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Title` varchar(160) NOT NULL DEFAULT 'Science Alert',
  `Sms` varchar(160) NOT NULL DEFAULT 'Science Alert',
  `ImportDate` datetime DEFAULT NULL,
  `PublishDate` datetime DEFAULT NULL,
  `Scheduled` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1177 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sciencealerts`
--

/*!40000 ALTER TABLE `sciencealerts` DISABLE KEYS */;
INSERT INTO `sciencealerts` (`Id`,`Title`,`Sms`,`ImportDate`,`PublishDate`,`Scheduled`) VALUES 
 (733,'MEMORIAL DAY SKYWATCHING: SEE HALF MOON PASS MARS','MEMORIAL DAY SKYWATCHING: SEE HALF MOON PASS MARS-Watch as a bright orange-yellow Mars and the moon cross paths.','2012-05-28 03:02:02','2012-05-27 17:14:00','N'),
 (734,'NATIONS WASTING TIME ON CLIMATE','NATIONS WASTING TIME ON CLIMATE-The latest round of UN climate talks makes little progress against a \"coalition of the unwilling\", observers say.','2012-05-28 03:02:04','2012-05-26 00:23:52','N'),
 (735,'HOW NOT TO EAT THE WRONG FROG','HOW NOT TO EAT THE WRONG FROG-Fringe-lipped bats can&rsquo;t be fooled by imitators of favored prey','2012-05-28 03:02:05','2012-05-26 01:16:47','N'),
 (736,'BUTTERFLIES SPREAD IN HOT SUMMERS','BUTTERFLIES SPREAD IN HOT SUMMERS-Once rare brown argus butterflies have been moving north due to a pattern of hot summers, say researchers.','2012-05-28 03:02:07','2012-05-26 00:23:52','N'),
 (737,'SPACEX DRAGON ATTACHED TO INTERNATIONAL SPACE STATION IN SPACEFLIGHT FIRST','SPACEX DRAGON ATTACHED TO INTERNATIONAL SPACE STATION IN SPACEFLIGHT FIRST-The International Space Station','2012-05-28 03:02:10','2012-05-27 04:42:12','N'),
 (738,'BOTTLED CARBON FROM MARS BODES WELL FOR ANCIENT ALIENS','BOTTLED CARBON FROM MARS BODES WELL FOR ANCIENT ALIENS-Carbon found in Martian rocks came from magma not alien life forms','2012-05-28 03:02:11','2012-05-24 23:15:45','N'),
 (739,'SYNCHRONIZED BRAINS: FEELING STRONG EMOTIONS MAKES PEOPLE','SYNCHRONIZED BRAINS: FEELING STRONG EMOTIONS MAKES PEOPLE-Human emotions are highly contagious. Seeing others','2012-05-28 03:02:12','2012-05-25 23:12:42','N'),
 (740,'STATION GRABS SPACEX DRAGON SHIP','STATION GRABS SPACEX DRAGON SHIP-The first commercial cargo ship to visit the space station is attached to the orbiting laboratory by a robotic arm.','2012-05-28 03:02:13','2012-05-24 23:31:00','N'),
 (741,'FAMILY  LABELS FRAMED SIMILARLY ACROSS CULTURES','FAMILY  LABELS FRAMED SIMILARLY ACROSS CULTURES-Descriptions of kin reflect trade-off between simplicity and utility','2012-05-28 03:02:14','2012-05-26 01:05:35','N'),
 (742,'DUST TO DUST: THE DEATH OF AN EXOPLANET','DUST TO DUST: THE DEATH OF AN EXOPLANET-Kepler has discovered a weird exoplanet candidate -- its being shredded to dust by its host star.','2012-05-28 03:02:14','2012-05-27 16:30:00','N'),
 (743,'ASTEROID NUDGED BY SUNLIGHT: MOST PRECISE MEASUREMENT OF YARKOVSKY EFFECT','ASTEROID NUDGED BY SUNLIGHT: MOST PRECISE MEASUREMENT OF YARKOVSKY EFFECT-Scientists on NASA','2012-05-28 03:02:15','2012-05-24 23:43:37','N'),
 (744,'VIRTUAL PATIENT UNDER THE KNIFE','VIRTUAL PATIENT UNDER THE KNIFE-How a giant touchscreen is teaching surgeons','2012-05-28 03:02:15','2012-05-25 18:39:00','N'),
 (745,'BIGFOOT: BEYOND FOOTPRINTS AND DNA','BIGFOOT: BEYOND FOOTPRINTS AND DNA-Bigfoot samples have long been subjected to scientific testing...and yet science has yet to confirm the sightings.','2012-05-28 03:02:20','2012-05-24 20:53:23','N'),
 (746,'BACTERIAL TRICK KEEPS ROBOTS IN SYNC','BACTERIAL TRICK KEEPS ROBOTS IN SYNC-Communicating environmental information allows a stumbling machine to rejoin its group','2012-05-28 03:02:20','2012-05-25 21:34:29','N'),
 (747,'METAMATERIALS, QUANTUM DOTS SHOW PROMISE FOR NEW TECHNOLOGIES','METAMATERIALS, QUANTUM DOTS SHOW PROMISE FOR NEW TECHNOLOGIES-Researchers are edging toward the creation of new optical technologies using','2012-05-28 03:02:22','2012-05-24 23:56:42','N'),
 (748,'LAB GROWS HEART MUSCLE FROM SKIN','LAB GROWS HEART MUSCLE FROM SKIN-Scientists in Israel say they have managed to turn patients own skin cells into heart muscle in the lab.','2012-05-28 03:02:22','2012-05-27 04:28:21','N'),
 (749,'WIND-DRIVEN MARS TUMBLEWEED ROVER TO ROLL THROUGH ROCKY TERRAIN?','WIND-DRIVEN MARS TUMBLEWEED ROVER TO ROLL THROUGH ROCKY TERRAIN?-New research shows that a wind-driven','2012-05-28 03:02:25','2012-05-25 07:23:53','N'),
 (750,'FLAME RETARDANT RISK VARIES BY RACE','FLAME RETARDANT RISK VARIES BY RACE-Despite equal levels of chemicals in their homes, nonwhite toddlers had more exposure.','2012-05-28 03:02:26','2012-05-25 07:23:53','N'),
 (751,'DELAY OF BLOOM BLAMED ON CLIMATE CHANGE','DELAY OF BLOOM BLAMED ON CLIMATE CHANGE-Flowers that fail to open early in spring may be taking cue from previous autumn','2012-05-28 03:02:28','2012-05-24 07:13:20','N'),
 (752,'EARLIEST MEMBER OF GIANT PANDA LINE FOUND IN SPAIN','EARLIEST MEMBER OF GIANT PANDA LINE FOUND IN SPAIN-A pair of 11-million-year-old fossilised teeth may have belonged to an ancestor of the giant panda','2012-05-28 03:02:28','2012-05-24 20:20:17','N'),
 (753,'TV REMOTE CONTROL INVENTOR DIES','TV REMOTE CONTROL INVENTOR DIES-The inventor of the television remote control, Eugene Polley, dies of natural causes, aged 96, in a Chicago hospital.','2012-05-28 03:02:30','2012-05-21 22:31:00','N'),
 (754,'WEED-KILLING LASERS BEING DEVELOPED','WEED-KILLING LASERS BEING DEVELOPED-Lasers take weed control to a whole new level.','2012-05-28 03:02:33','2012-05-25 00:05:35','N'),
 (755,'RAPID DNA SEQUENCING MAY SOON BE ROUTINE PART OF EACH PATIENT','RAPID DNA SEQUENCING MAY SOON BE ROUTINE PART OF EACH PATIENT-Rapid DNA sequencing may soon become a routine part of each individual','2012-05-28 03:02:33','2012-05-26 18:16:18','N'),
 (765,'WEIGHT-LOSS SURGERY REDUCES DESIRE FOR ALCOHOL','WEIGHT-LOSS SURGERY REDUCES DESIRE FOR ALCOHOL-The Roux-en-Y weight-loss procedure appears to leave patients averse to alcohol','2012-05-28 03:02:49','2012-05-24 19:30:00','N'),
 (766,'LONG-ACTING CONTRACEPTIVES BEST BY FAR','LONG-ACTING CONTRACEPTIVES BEST BY FAR-Implants and IUDs outperform the pill and other birth control options','2012-05-28 03:02:50','2012-05-24 03:00:22','N'),
 (768,'WEARABLE ENERGY: T-SHIRTS COULD CHARGE PHONES','WEARABLE ENERGY: T-SHIRTS COULD CHARGE PHONES-New tech could allow your clothes to do double duty.','2012-05-28 03:02:51','2012-05-25 22:38:00','N'),
 (769,'SPACE LAWYER: BEFORE HUMANS STEP INTO COMMERCIAL SPACEFLIGHT, LAWS NEED GIANT LEAP','SPACE LAWYER: BEFORE HUMANS STEP INTO COMMERCIAL SPACEFLIGHT, LAWS NEED GIANT LEAP-SpaceX','2012-05-28 03:02:55','2012-05-22 23:17:47','N'),
 (770,'TWIDN: BIGFOOT','TWIDN: BIGFOOT-Do you know any bigfeet? If you do youd better send a DNA sample to Oxford!','2012-05-28 03:02:57','2012-05-25 21:11:47','N'),
 (771,'FOR KIDS: YOUNG SCIENTISTS','FOR KIDS: YOUNG SCIENTISTS-High school student wins $75,000 prize for pancreatic cancer test, others show off work','2012-05-28 03:02:58','2012-05-24 02:54:41','N'),
 (772,'THE PILL: FOR MEN!: DNEWS NUGGETS','THE PILL: FOR MEN!: DNEWS NUGGETS-A contraception pill for men might be possible in the near future.','2012-05-28 03:03:03','2012-05-25 20:35:11','N'),
 (773,'NO NEW SMELL CELLS','NO NEW SMELL CELLS-Unlike other mammals, humans don’t make new olfactory neurons, a study suggests','2012-05-28 03:03:05','2012-05-24 01:45:10','N'),
 (775,'CHIMPANZEES BEHAVE LIKE PEOPLE','CHIMPANZEES BEHAVE LIKE PEOPLE-Chimps and orangutans really do have \"personalities like people\", new research says','2012-05-28 03:03:06','2012-05-23 14:45:28','N'),
 (776,'SEEKING NEUTRINO CLUES TO MISSING SUPERNOVAE','SEEKING NEUTRINO CLUES TO MISSING SUPERNOVAE-Half the universes supernovae explode without a trace.','2012-05-28 03:03:09','2012-05-10 12:30:00','N'),
 (777,'WHERE DOES ORGASM LIVE?','WHERE DOES ORGASM LIVE?-We know surprisingly little about sex and orgasm when it comes to brain activity.','2012-05-28 03:03:10','2012-05-25 20:10:16','N'),
 (778,'FROSTED HONEYCOMB OF A MOON','FROSTED HONEYCOMB OF A MOON-Cassini images reveal ice on Saturn satellite Hyperion','2012-05-28 03:03:10','2012-05-23 21:11:09','N'),
 (779,'COMMONLY USED PESTICIDE TURNS HONEY BEES INTO','COMMONLY USED PESTICIDE TURNS HONEY BEES INTO-Biologists have discovered that a small dose of a commonly used crop pesticide turns honey bees into','2012-05-28 03:03:13','2012-05-24 18:59:29','N'),
 (780,'DOGS, BOOZE AND BLING: NORTHERN IRELANDS MEDIEVAL SHOPPING MALL','DOGS, BOOZE AND BLING: NORTHERN IRELANDS MEDIEVAL SHOPPING MALL-Excavations reveal a medieval luxury shopping mall','2012-05-28 03:03:16','2012-05-25 16:39:57','N'),
 (781,'RARE ARABIAN LEOPARD IN NEED OF SAFE CORRIDORS','RARE ARABIAN LEOPARD IN NEED OF SAFE CORRIDORS-Arabian leopard bred for release into wild','2012-05-28 03:03:18','2012-05-25 05:29:16','N'),
 (783,'JAPANS FUGITIVE PENGUIN BACK IN CAPTIVITY','JAPANS FUGITIVE PENGUIN BACK IN CAPTIVITY-The birds last moments of freedom were lived on a riverbank five miles from its home.','2012-05-28 03:03:19','2012-05-25 20:01:00','N'),
 (784,'SILICON VALLEY REACHES FOR THE SKY','SILICON VALLEY REACHES FOR THE SKY-An experimental, entrepreneurial spirit may be just what space exploration needs','2012-05-28 03:03:20','2012-05-09 22:30:00','N'),
 (786,'TEA COULD AID OLYMPIC CHEATING','TEA COULD AID OLYMPIC CHEATING-Researchers have found that green and white teas could hide abnormal levels of testosterone in athletes.','2012-05-28 03:03:23','2012-05-21 21:23:53','N'),
 (789,'BLOODHOUND DIARY: FLYING SCOTSMAN','BLOODHOUND DIARY: FLYING SCOTSMAN-Worlds fastest car will have wheels made in Scotland','2012-05-28 03:03:24','2012-05-24 18:37:00','N'),
 (790,'CLOAK OF INVISIBILITY: ENGINEERS USE PLASMONICS TO CREATE AN INVISIBLE PHOTODETECTOR','CLOAK OF INVISIBILITY: ENGINEERS USE PLASMONICS TO CREATE AN INVISIBLE PHOTODETECTOR-Engineers have for the first time used','2012-05-28 03:03:28','2012-05-21 20:16:46','N'),
 (791,'CREATING THE PERFECT GARDEN','CREATING THE PERFECT GARDEN-Flower Show winners guide to the perfect garden','2012-05-28 03:03:28','2012-05-25 06:36:38','N'),
 (792,'IMPERFECT CHIP PRETTY DARN GOOD','IMPERFECT CHIP PRETTY DARN GOOD-High error rates no obstacle in some applications','2012-05-28 03:03:28','2012-05-23 01:29:26','N'),
 (794,'NO ALIENS ON MARS? NO PROBLEM, WE WILL LOOK ELSEWHERE','NO ALIENS ON MARS? NO PROBLEM, WE WILL LOOK ELSEWHERE-If the Mars Science Lab doesnt find life when it lands in August, we will keep looking','2012-05-28 03:03:29','2012-05-02 22:30:00','N'),
 (795,'PARKING JERKS BEWARE!','PARKING JERKS BEWARE!-Moscow-based online newspaper creates app that aims to humiliate Parking Douches into changing their ways.','2012-05-28 03:03:31','2012-05-25 18:59:29','N'),
 (796,'CALIFORNIA METEORITE IS RARE ROCK LADEN WITH ORGANICS','CALIFORNIA METEORITE IS RARE ROCK LADEN WITH ORGANICS-Fragments of the meteorite have revealed that it is much more exciting than scientists thought','2012-05-28 03:03:33','2012-05-01 20:59:00','N'),
 (797,'RETURN OF THE VACUUM TUBE','RETURN OF THE VACUUM TUBE-Retro technology makes a comeback in a nanoscale transistor that is lightweight, low cost, and long lasting.','2012-05-28 03:03:35','2012-05-18 22:56:26','N'),
 (798,'DNA USED AS REWRITABLE DATA STORAGE IN CELLS','DNA USED AS REWRITABLE DATA STORAGE IN CELLS-Genetically encoded memory could track cell division inside the body','2012-05-28 03:03:36','2012-05-22 03:09:13','N'),
 (799,'MEASURING TRANSIENT X-RAYS WITH LOBSTER EYES','MEASURING TRANSIENT X-RAYS WITH LOBSTER EYES-A technology that mimics the structure of a lobster','2012-05-28 03:03:40','2012-05-18 19:57:27','N'),
 (801,'PUMPING GROUNDWATER RAISES SEA LEVEL','PUMPING GROUNDWATER RAISES SEA LEVEL-Two new studies flag an underreported factor in global ocean change','2012-05-28 03:03:43','2012-05-22 01:58:38','N'),
 (803,'WHY WE DONT NEED THE MEN IN BLACK','WHY WE DONT NEED THE MEN IN BLACK-Protecting the Earth from the scum of the Universe isnt so hard when youve got the Fermi Paradox on your side.','2012-05-28 03:03:50','2012-05-25 15:30:00','N'),
 (804,'LISTENING TO CHICKENS COULD IMPROVE POULTRY PRODUCTION','LISTENING TO CHICKENS COULD IMPROVE POULTRY PRODUCTION-Listening to squawks and other chicken','2012-05-28 03:03:50','2012-05-17 00:52:22','N'),
 (805,'CLIMATE CHANGE MISCUES MAY SHRINK SPECIES’ OUTER LIMITS','CLIMATE CHANGE MISCUES MAY SHRINK SPECIES’ OUTER LIMITS-Ecological partnerships are getting out of sync, especially at high latitudes','2012-05-28 03:03:51','2012-05-21 21:51:17','N'),
 (806,'REVERSIBLE DOPING: HYDROGEN FLIPS SWITCH ON VANADIUM OXIDE','A DOLPHIN DEATH WHODUNIT IN PERU-The Peruvian government claims that nearly 900 dolphins died of natural causes. A separate study disagrees.','2012-05-28 03:03:54','2012-05-21 20:12:42','N'),
 (807,'WANT TO LOSE WEIGHT? SKIP THESE DIETS','WANT TO LOSE WEIGHT? SKIP THESE DIETS-Potentially dangerous crash diets wont help you lose those extra pounds ahead of the bathing suit season.','2012-05-28 03:03:54','2012-05-25 06:04:55','N'),
 (810,'A DOLPHIN DEATH WHODUNIT IN PERU','A DOLPHIN DEATH WHODUNIT IN PERU-The Peruvian government claims that nearly 900 dolphins died of natural causes. A separate study disagrees.','2012-05-28 03:04:14','2012-05-25 01:36:35','N'),
 (812,'MARS TUMBLEWEED ROVERS WILL ROCK, BOUNCE AND ROLL','MARS TUMBLEWEED ROVERS WILL ROCK, BOUNCE AND ROLL-The tumbleweed rover could dominate Mars exploration, but is there too much bounce in its roll?','2012-05-28 03:04:24','2012-05-25 01:28:09','N'),
 (813,'EXTINCT BUMBLEBEE RETURNS TO UK','EXTINCT BUMBLEBEE RETURNS TO UK-The short-haired bumblebee, which vanished from the UK in 1988, is being reintroduced to the Kent countryside.','2012-05-28 23:24:02','2012-05-28 10:23:15','N'),
 (814,'INBREEDING THREATENS MEERKATS','INBREEDING THREATENS MEERKATS-The survival chances of meerkats are being threatened by inbreeding, according to a new 20-year study in the Kalahari.','2012-05-28 23:24:03','2012-05-28 06:12:35','N'),
 (815,'RED PANDA BOOSTS BREEDING PLANS','RED PANDA BOOSTS BREEDING PLANS-A male red panda arrives at a conservation park in southern Scotland as part of a European captive breeding programme.','2012-05-28 23:24:06','2012-05-28 06:13:57','N'),
 (817,'SMALLEST DEER THREATENED BY PETS','SMALLEST DEER THREATENED BY PETS-Worlds smallest deer is hounded by dogs','2012-05-28 23:24:10','2012-05-28 07:55:54','N'),
 (819,'TEEN SOLVES 350-YEAR-OLD MATH PROBLEM','TEEN SOLVES 350-YEAR-OLD MATH PROBLEM-The 16-year-old boy said he \"didnt believe there couldnt be a solution.\"','2012-05-28 23:25:04','2012-05-28 18:56:36','N'),
 (821,'HOW TO USE JUST ONE PAPER TOWEL','HOW TO USE JUST ONE PAPER TOWEL-You wont even need to wipe your hands on your pants.','2012-05-28 23:25:06','2012-05-28 18:06:52','N'),
 (822,'ASTRONAUTS TO SPEND MEMORIAL DAY UNPACKING','ASTRONAUTS TO SPEND MEMORIAL DAY UNPACKING-Therell be no relaxing this long weekend for astronauts aboard the space station.','2012-05-28 23:25:06','2012-05-28 02:19:00','N'),
 (823,'WERE THE CAVEMEN OF THE DANUBE FLUTISTS?','WERE THE CAVEMEN OF THE DANUBE FLUTISTS?-Bone flutes found in southern Germany push back the date human creativity evolved.','2012-05-28 23:25:08','2012-05-25 01:10:00','N'),
 (824,'SPECIAL EFFECTS GETTING CRAZY: GOTTA-SEE VIDEOS','SPECIAL EFFECTS GETTING CRAZY: GOTTA-SEE VIDEOS-In the new Men in black movie the special effects are eye-popping, literally.','2012-05-28 23:25:09','2012-05-25 01:07:09','N'),
 (825,'TILE YOUR WALL WITH VIDEO SCREENS','TILE YOUR WALL WITH VIDEO SCREENS-A demo suggests one way to make enormous TVs not insanely expensive.','2012-05-28 23:25:10','2012-05-25 00:27:37','N'),
 (826,'THOUSAND-YEAR-OLD MUMMIES FOUND IN PERU','THOUSAND-YEAR-OLD MUMMIES FOUND IN PERU-A tomb yields more than 80 mummies and skeletons -- many belonging to babies.','2012-05-28 23:25:11','2012-05-25 00:18:36','N'),
 (827,'ORGANIC FARMING HELPS VETS RECOVER FROM WAR','ORGANIC FARMING HELPS VETS RECOVER FROM WAR-Military veterans are trading green fatigues for green thumbs in San Diego.','2012-05-28 23:25:12','2012-05-25 00:05:44','N'),
 (828,'DEVICE MAY LET HUMANS COMMUNICATE WITH DOLPHINS','DEVICE MAY LET HUMANS COMMUNICATE WITH DOLPHINS-A prototype dolphin speaker projects the full range of all dolphin-made sounds.','2012-05-28 23:25:12','2012-05-24 23:23:00','N'),
 (830,'THE DEMISE OF GUYS LIKELY REAL: DNEWS NUGGETS','THE DEMISE OF GUYS LIKELY REAL: DNEWS NUGGETS-Are the worlds men being destroyed by online pornography and video games?','2012-05-28 23:25:13','2012-05-24 22:01:06','N'),
 (831,'BABIES KNOW WHATS BORING','BABIES KNOW WHATS BORING-Babies as young as 7 months can parse out boring versus interesting, research shows.','2012-05-28 23:25:14','2012-05-24 21:08:00','N'),
 (832,'IPADS COULD SCAN PALMS FOR PASSWORDS','IPADS COULD SCAN PALMS FOR PASSWORDS-Tablets may soon authenticate users by reading hand movement.','2012-05-28 23:25:15','2012-05-24 21:07:00','N'),
 (834,'LIGHTNING ROD PREVENTS STRIKES','LIGHTNING ROD PREVENTS STRIKES-This rod prevents strikes by doing exactly the opposite of what they are usually known for.','2012-05-28 23:25:16','2012-05-24 19:00:51','N'),
 (835,'HUBBLE SEES A SPIRAL WITHIN A SPIRAL','HUBBLE SEES A SPIRAL WITHIN A SPIRAL-NASA','2012-05-28 23:25:20','2012-05-28 20:20:50','N'),
 (837,'MORPHING ENGINES MIGHT CUT AEROPLANE NOISE','MORPHING ENGINES MIGHT CUT AEROPLANE NOISE-Jet engines that can subtly adjust their shape could help cut noise by changing the way the air flows','2012-05-28 23:25:35','2012-05-28 19:18:00','N'),
 (838,'GAMIFY YOUR SWEAT AND MAKE FILMS MORE EXCITING','GAMIFY YOUR SWEAT AND MAKE FILMS MORE EXCITING-A new smartphone app will monitor your response to a film or game','2012-05-28 23:25:36','2012-05-28 17:24:30','N'),
 (839,'TELESCOPE INSTRUMENT FLIES TO US','TELESCOPE INSTRUMENT FLIES TO US-The first instrument to be built for the successor to the Hubble space telescope heads out of London for Washington.','2012-05-30 00:18:03','2012-05-29 23:50:57','N'),
 (840,'DORMICE WHISKERS AID CLIMBING','DORMICE WHISKERS AID CLIMBING-Dormice use their whiskers to sense where they are going as they scale trees, researchers say.','2012-05-30 00:18:05','2012-05-29 12:42:27','N'),
 (841,'NASA TEST SPOTS EARLY BONE LOSS','NASA TEST SPOTS EARLY BONE LOSS-Nasa scientists believe they have found a way to spot osteoporosis bone loss at the earliest stages of the disease.','2012-05-30 00:18:06','2012-05-29 06:44:16','N'),
 (842,'THINKING OUTSIDE THE BOX IN SPACE','THINKING OUTSIDE THE BOX IN SPACE-Xbox Kinect motion sensor system used to dock satellites in orbit','2012-05-30 00:18:09','2012-05-29 16:23:58','N'),
 (844,'RFID TECHNOLOGY THWARTS BIRDS NEST COUNTERFEITERS','RFID TECHNOLOGY THWARTS BIRDS NEST COUNTERFEITERS-Protecting Chinas diners from counterfeit birds nest soup','2012-05-30 00:18:11','2012-05-29 04:30:07','N'),
 (845,'HARAPPANS MAY HAVE LIVED, DIED BY MONSOON','HARAPPANS MAY HAVE LIVED, DIED BY MONSOON-Ancient Indus civilization shaped by seasonal rains','2012-05-30 00:18:15','2012-05-29 22:33:40','N'),
 (846,'SCIENTISTS DEPLOY GENETICS IN SEARCH FOR BIGFOOT','SCIENTISTS DEPLOY GENETICS IN SEARCH FOR BIGFOOT-LONDON (Reuters) - Scientists are turning to genetic testing to see if they can prove the','2012-05-30 00:18:32','2012-05-22 19:32:37','N'),
 (847,'EVIL EYEBROWS AND POINTY CHIN OF A CARTOON VILLAIN MAKE OUR','EVIL EYEBROWS AND POINTY CHIN OF A CARTOON VILLAIN MAKE OUR-Why do the evil eyebrows and pointy chin of a cartoon villain make our','2012-05-30 00:18:41','2012-05-29 17:16:46','N'),
 (848,'BEETLE FLIGHT: FLAPPING PROTECTIVE WINGS INCREASE LIFT','BEETLE FLIGHT: FLAPPING PROTECTIVE WINGS INCREASE LIFT-The forewings of beetles, the elytra, are hardened structures which protect the insect','2012-05-30 00:18:42','2012-05-29 17:08:38','N'),
 (849,'PEOPLE SMILE WHEN THEY ARE FRUSTRATED, AND THE COMPUTER KNOWS THE DIFFERENCE','PEOPLE SMILE WHEN THEY ARE FRUSTRATED, AND THE COMPUTER KNOWS THE DIFFERENCE-Do you smile when you','2012-05-30 00:18:43','2012-05-29 00:57:27','N'),
 (852,'GREAT DYING RECOVERY TOOK 10 MILLION YEARS','GREAT DYING RECOVERY TOOK 10 MILLION YEARS-Living, breathing organisms likely hit several stumbling blocks along the road to recovery.','2012-05-30 00:19:13','2012-05-29 20:35:00','N'),
 (854,'THE NEXT-IPHONE SEASON DRAWS NEAR, READ WISELY','THE NEXT-IPHONE SEASON DRAWS NEAR, READ WISELY-How to read stories about the next iPhone intelligently.','2012-05-30 00:19:17','2012-05-29 18:58:43','N'),
 (855,'SPACEX CAPSULE HAS NEW CAR SMELL','SPACEX CAPSULE HAS NEW CAR SMELL-After the hatches between the space station and the Dragon','2012-05-30 00:19:22','2012-05-27 02:37:00','N'),
 (858,'LET THE CREATIVE JUICES FLOW THROUGH SOCIAL NETWORKS','LET THE CREATIVE JUICES FLOW THROUGH SOCIAL NETWORKS-The internet is more than a mash-up of ideas – its social networks can spark the creative process','2012-05-30 00:20:16','2012-05-29 20:17:00','N'),
 (1027,'SHEEP FREE OF CHERNOBYL CONTROLS','SHEEP FREE OF CHERNOBYL CONTROLS-The last farms under sheep movement controls after the Chernobyl nuclear disaster finally have them lifted after 26 years.','2012-06-03 15:45:05','2012-06-01 20:26:14','N'),
 (1028,'UK BUTTERFLIES CONTINUE DECLINE','UK BUTTERFLIES CONTINUE DECLINE-The population of UK butterflies is continuing its downward trend, according to a nationwide survey of the countryside.','2012-06-03 15:45:05','2012-06-01 07:15:06','N'),
 (1029,'MALE CONTACT MAKES WOMAN GLOW','MALE CONTACT MAKES WOMAN GLOW-The merest interaction with a member of the opposite sex can bring a glow to a womans face, a new study finds.','2012-06-03 15:45:05','2012-05-30 21:22:43','N'),
 (1030,'GENOME PROMISES TASTIER TOMATOES','GENOME PROMISES TASTIER TOMATOES-Scientists have sequenced the tomato genome and say it will yield tastier fruit as a result.','2012-06-03 15:45:05','2012-05-30 22:34:29','N'),
 (1031,'THE CRAZE FOR LUCID DREAMING','THE CRAZE FOR LUCID DREAMING-The growing hobby of trying to control dreams','2012-06-03 15:45:05','2012-05-31 16:52:08','N'),
 (1032,'REPTILE HAS STEAK-KNIFE TEETH','REPTILE HAS STEAK-KNIFE TEETH-The unique saw-like chew of a New Zealand reptile could be the secret of its success, say scientists.','2012-06-03 15:45:05','2012-05-30 05:07:29','N'),
 (1033,'LEGACY OF BHOPAL DISASTER POISONS OLYMPICS','LEGACY OF BHOPAL DISASTER POISONS OLYMPICS-Bhopal disaster overshadows Indias participation in Olympics','2012-06-03 15:45:05','2012-05-30 13:41:59','N'),
 (1034,'HOW CITIES CAN BECOME HEALTHY PLACES','HOW CITIES CAN BECOME HEALTHY PLACES-How urban living can help people to lead healthy lives','2012-06-03 15:45:05','2012-05-30 12:18:51','N'),
 (1035,'BUFFER ZONE CALL FOR WIND FARMS','BUFFER ZONE CALL FOR WIND FARMS-Wind farms should be further from homes to protect people from noise in some circumstances, a group of AMs says.','2012-06-03 15:45:05','2012-05-30 11:05:48','N'),
 (1037,'RIDLEY SCOTT RETURNS TO ALIEN','RIDLEY SCOTT RETURNS TO ALIEN-Sir Ridley Scotts Alien prequel asks deep questions','2012-06-03 15:45:05','2012-06-01 14:32:07','N'),
 (1039,'AN ALL-NATURAL ANIMAL ORCHESTRA','AN ALL-NATURAL ANIMAL ORCHESTRA-The secret message hidden in the sounds of nature','2012-06-03 15:45:05','2012-06-01 01:57:26','N'),
 (1042,'ANTIAGING PROTEIN HELPS SET DAILY RHYTHMS','ANTIAGING PROTEIN HELPS SET DAILY RHYTHMS-Changing levels of sirtuin alter activity patterns in mice','2012-06-03 15:45:07','2012-06-02 01:59:04','N'),
 (1043,'FLEROVIUM AND LIVERMORIUM DEBUT ON PERIODIC TABLE','FLEROVIUM AND LIVERMORIUM DEBUT ON PERIODIC TABLE-New element names honor work of Russian and American laboratories','2012-06-03 15:45:07','2012-06-02 00:12:58','N'),
 (1044,'STONE AGE ART GETS ANIMATED','STONE AGE ART GETS ANIMATED-Ancient cave paintings depict  moving animals','2012-06-03 15:45:07','2012-06-01 20:52:51','N'),
 (1045,'QUANTUM TELEPORTATION LEAPS FORWARD','QUANTUM TELEPORTATION LEAPS FORWARD-Two teams improve long-distance transmission of information about particles','2012-06-03 15:45:07','2012-06-01 02:17:31','N'),
 (1046,'MILKY WAY WILL BE HIT HEAD-ON','MILKY WAY WILL BE HIT HEAD-ON-Andromeda galaxy will smash directly into ours','2012-06-03 15:45:07','2012-06-01 01:56:33','N'),
 (1047,'POPPIES MAKE MORE THAN OPIUM','POPPIES MAKE MORE THAN OPIUM-Gene cluster controls production of a valuable compound','2012-06-03 15:45:07','2012-05-31 23:56:00','N'),
 (1048,'TREATMENT HELPS PARALYZED RATS WALK','TREATMENT HELPS PARALYZED RATS WALK-Combination of drugs, electrical stimulation and therapy restores lost connections','2012-06-03 15:45:07','2012-05-31 23:37:02','N'),
 (1049,'BLUE-GREEN ALGAE RELEASE CHEMICAL SUSPECTED IN SOME AMPHIBIAN DEFORMITIES','BLUE-GREEN ALGAE RELEASE CHEMICAL SUSPECTED IN SOME AMPHIBIAN DEFORMITIES-Retinoic acid levels high in waterways rich in cyanobacteria blooms','2012-06-03 15:45:07','2012-05-31 03:11:41','N'),
 (1050,'SUPERVOLCANOES EVOLVE SUPERQUICKLY','SUPERVOLCANOES EVOLVE SUPERQUICKLY-Huge underground magma chambers appear and erupt within just several hundred  years','2012-06-03 15:45:07','2012-05-31 03:01:16','N'),
 (1052,'FOR KIDS: COOL JOBS: WIDE WORLD OF ROBOTS','FOR KIDS: COOL JOBS: WIDE WORLD OF ROBOTS-The machines can help with everything from surgery to disaster response','2012-06-03 15:45:07','2012-05-30 23:33:14','N'),
 (1053,'FOR KIDS: CAECILIANS: THE OTHER AMPHIBIAN','FOR KIDS: CAECILIANS: THE OTHER AMPHIBIAN-Legless creatures live secretive, strange existences underground and underwater','2012-06-03 15:45:07','2012-05-30 23:30:00','N'),
 (1054,'FEVER IN PREGNANCY LINKED TO AUTISM','FEVER IN PREGNANCY LINKED TO AUTISM-Women who run a high temperature during gestation may double risk of having an autistic child','2012-06-03 15:45:07','2012-05-30 22:35:47','N'),
 (1055,'FOR KIDS: HOW CREATIVITY POWERS SCIENCE','FOR KIDS: HOW CREATIVITY POWERS SCIENCE-Some of the best ideas come not from poring over the facts but from a walk in the woods','2012-06-03 15:45:07','2012-05-30 20:08:32','N'),
 (1056,'NEW SCIENTIST - SPACE','NEW SCIENTIST - SPACE-The Dragon space freighter is a slick piece of engineering, but still has a fair way to go','2012-06-03 15:45:10','2012-06-01 16:36:00','N'),
 (1057,'PRODUCING ARTIFICIAL BONES FROM FISH SCALES','PRODUCING ARTIFICIAL BONES FROM FISH SCALES-Scientists have developed technology for producing artificial bones from fish scales and apatite.','2012-06-03 15:45:15','2012-06-01 19:00:30','N'),
 (1058,'SEX: IT','SEX: IT-Way more than fun and games, sexual reproduction appears to give an evolutionary advantage, biologists have discovered.','2012-06-03 15:45:15','2012-06-01 00:25:55','N'),
 (1059,'NANODEVICE MANUFACTURING STRATEGY USING DNA','NANODEVICE MANUFACTURING STRATEGY USING DNA-Researchers have developed a method for building complex nanostructures out of interlocking DNA','2012-06-03 15:45:15','2012-05-31 00:52:22','N'),
 (1064,'X-RAY','X-RAY-Astronomers have identified a long-sought X-ray','2012-06-03 15:45:16','2012-06-01 02:27:57','N'),
 (1065,'X-RAY LASER PROBES BIOMOLECULES TO INDIVIDUAL ATOMS','X-RAY LASER PROBES BIOMOLECULES TO INDIVIDUAL ATOMS-Scientists have demonstrated how the world','2012-06-03 15:45:16','2012-06-01 00:27:57','N'),
 (1066,'ALZHEIMER','ALZHEIMER-The molecular structure of a protein involved in Alzheimer','2012-06-03 15:45:16','2012-06-01 00:27:57','N'),
 (1067,'SPACEX DRAGON CAPSULE RETURNS TO EARTH AFTER FIRST COMMERCIAL FLIGHT TO SPACE STATION','SPACEX DRAGON CAPSULE RETURNS TO EARTH AFTER FIRST COMMERCIAL FLIGHT TO SPACE STATION-SpaceX','2012-06-03 15:45:16','2012-05-31 23:07:37','N'),
 (1070,'MORE ATOMIC HYDROGEN GAS LURKS IN UNIVERSE: THERE','MORE ATOMIC HYDROGEN GAS LURKS IN UNIVERSE: THERE-More atomic hydrogen gas -- the ultimate fuel for stars -- is lurking in today','2012-06-03 15:45:16','2012-05-30 21:28:58','N'),
 (1071,'ARCTIC BACTERIA HELP IN THE SEARCH TO FIND LIFE ON JUPITER','Science Alert','2012-06-03 15:45:16','2012-05-30 19:34:04','N'),
 (1072,'DOG NAMED BLUE STIRS UP TOWN: DNEWS NUGGETS','DOG NAMED BLUE STIRS UP TOWN: DNEWS NUGGETS-Blue is homeless by choice: he likes wandering. But some in Elephant Butte, N.M., want him on a leash.','2012-06-03 15:45:18','2012-06-02 01:23:18','N'),
 (1073,'LIP SMACKS OF MONKEYS PRELUDE TO SPEECH?','LIP SMACKS OF MONKEYS PRELUDE TO SPEECH?-The rapid, controlled movements of the tongue, lips and jaw shed light on the evolution of human speech.','2012-06-03 15:45:18','2012-06-02 00:46:09','N'),
 (1074,'TEXAS THUNDERCLOUD CAUGHT ON VIDEO','TEXAS THUNDERCLOUD CAUGHT ON VIDEO-The saucer-shaped cloud that formed over Texas last week created one of the most severe kinds of storms.','2012-06-03 15:45:18','2012-06-02 00:07:00','N'),
 (1075,'DOUBLE YOUR PLEASURE WITH TRANSPARENT SMARTPHONE','DOUBLE YOUR PLEASURE WITH TRANSPARENT SMARTPHONE-Prototype smartphone boasts touch panels on both sides of a see-through display.','2012-06-03 15:45:18','2012-06-01 22:31:40','N'),
 (1076,'OUR EXOPLANET VIEW OF VENUS IS COMING','OUR EXOPLANET VIEW OF VENUS IS COMING-Next week, Venus will partake in a spectacle that wont happen for another century.','2012-06-03 15:45:18','2012-06-01 22:16:52','N'),
 (1079,'AVOID PACIFIC BLUEFIN TUNA, RADIOACTIVE OR NOT','AVOID PACIFIC BLUEFIN TUNA, RADIOACTIVE OR NOT-Its time to take protection of bluefin tuna to the next level, radioactive or not.','2012-06-03 15:45:18','2012-06-01 21:08:04','N'),
 (1080,'MAN EATS HEART OF ROOMMATE: DNEWS NUGGETS','MAN EATS HEART OF ROOMMATE: DNEWS NUGGETS-How many human incidents need to happen before we believe in the Zombie Apocalypse?','2012-06-03 15:45:18','2012-06-01 20:32:04','N'),
 (1081,'TWIDN: CANNIBALS WONT GET TO LEAVE EARTH','TWIDN: CANNIBALS WONT GET TO LEAVE EARTH-Do you know how to avoid the zombie apocalypse in Miami and see Venus transit the sun?','2012-06-03 15:45:18','2012-06-01 19:54:39','N'),
 (1082,'GOOGLE WANTS .LOL DOMAIN: DNEWS NUGGETS','GOOGLE WANTS .LOL DOMAIN: DNEWS NUGGETS-The search-engine giant has applied for its own top-level domains, including .google and .youtube.','2012-06-03 15:45:18','2012-06-01 19:49:14','N'),
 (1083,'TIGHTROPE WALKER WILL ATTEMPT NIAGARA FALLS','TIGHTROPE WALKER WILL ATTEMPT NIAGARA FALLS-Nik Wallenda will make the first attempt at crossing Niagara Falls on a tightrope in over 100 years on June 15.','2012-06-03 15:45:18','2012-06-01 19:22:40','N'),
 (1084,'MOVIE FRAMES SAVED TO ATOMIC VAPOR','MOVIE FRAMES SAVED TO ATOMIC VAPOR-Physicists give new meaning to the \"cloud storage.\"','2012-06-03 15:45:18','2012-06-01 18:24:16','N'),
 (1085,'WHERES THAT MUSIC COMING FROM? YOUR GRILL!','WHERES THAT MUSIC COMING FROM? YOUR GRILL!-This dental jewelry vibrates music through the teeth to the ear drum.','2012-06-03 15:45:18','2012-06-01 18:18:13','N'),
 (1086,'VINTAGE 1976 APPLE 1 ON AUCTION','VINTAGE 1976 APPLE 1 ON AUCTION-Own a historic piece of the Information Age...if you can afford it.','2012-06-03 15:45:18','2012-06-01 18:08:35','N'),
 (1087,'BAT, BEE, FROG DEATHS MAY BE LINKED','BAT, BEE, FROG DEATHS MAY BE LINKED-Why has disease been killing off bats, bees and frogs? Shared factors may link the deaths.','2012-06-03 15:45:18','2012-06-01 17:30:00','N'),
 (1088,'MATH TO HELP COMPUTERS GET SARCASM: DNEWS NUGGETS','MATH TO HELP COMPUTERS GET SARCASM: DNEWS NUGGETS-A mathematical equation is designed to help computers understand \"whatever.\"','2012-06-03 15:45:18','2012-06-01 16:47:52','N'),
 (1089,'SUPER VOLCANOES FORM SUPER FAST','SUPER VOLCANOES FORM SUPER FAST-Super-volcanoes may go from zero to apocalypse in only a few centuries.','2012-06-03 15:45:18','2012-06-01 08:44:20','N'),
 (1091,'MILKY WAY DOOMED TO CRASH WITH ANDROMEDA','MILKY WAY DOOMED TO CRASH WITH ANDROMEDA-Over time, the huge galactic smashup will create an entirely new hybrid galaxy.','2012-06-03 15:45:18','2012-06-01 02:15:00','N'),
 (1092,'TV HELPS WHITE BOYS CONFIDENCE: DNEWS NUGGETS','TV HELPS WHITE BOYS CONFIDENCE: DNEWS NUGGETS-A study of 400 kids who watched TV a lot found the self esteem of African American boys and all girls harmed.','2012-06-03 15:45:18','2012-06-01 01:50:09','N'),
 (1093,'PARALYZED RATS REGAIN STRUT','PARALYZED RATS REGAIN STRUT-Rats with spinal cord damage are able to walk with the help of a robot.','2012-06-03 15:45:18','2012-06-01 01:27:00','N'),
 (1096,'AMATEUR ASTRONOMERS BOOST ASTEROID HUNT','AMATEUR ASTRONOMERS BOOST ASTEROID HUNT-Yet again young amateur astronomers are helping scientists with real science.','2012-06-03 15:45:18','2012-05-31 21:31:00','N'),
 (1098,'BRA DESIGNED FOR SENSITIVE SKIN: DNEWS NUGGETS','BRA DESIGNED FOR SENSITIVE SKIN: DNEWS NUGGETS-A bra for cancer patients has been designed with non-instrusive seam lines and soft, breathable fabric.','2012-06-03 15:45:18','2012-05-31 19:22:42','N'),
 (1099,'MIND-READING ROBOT TEACHERS HEAD TO CLASS','MIND-READING ROBOT TEACHERS HEAD TO CLASS-Robot teacher uses engaging techniques to keep students awake in class.','2012-06-03 15:45:18','2012-05-31 18:36:50','N'),
 (1100,'GARDEN WHEEL IDEA CAME FROM OUTER SPACE','GARDEN WHEEL IDEA CAME FROM OUTER SPACE-This in-home rotary herb garden design was inspired by a NASA concept for feeding astronauts in space.','2012-06-03 15:45:18','2012-05-31 18:05:40','N'),
 (1101,'IN CASE OF CYBER ATTACK...?','IN CASE OF CYBER ATTACK...?-Cyber-attacks on government systems have increased 650 percent between 2006 and 2010.','2012-06-03 15:45:18','2012-05-31 17:59:00','N'),
 (1102,'SPACEX DRAGON CAPSULE DEPARTS SPACE STATION','SPACEX DRAGON CAPSULE DEPARTS SPACE STATION-The first privately owned space vehicle to reach the space station is on its way back to Earth.','2012-06-03 15:45:18','2012-05-31 15:18:35','N'),
 (1103,'MICROREACTORS TO PRODUCE EXPLOSIVE MATERIALS','MICROREACTORS TO PRODUCE EXPLOSIVE MATERIALS-The larger the reaction vessel, the quicker products can be made','2012-06-03 15:45:19','2012-05-30 19:32:02','N'),
 (1104,'GO FOR A JOG WITH A HELICOPTER DRONE','GO FOR A JOG WITH A HELICOPTER DRONE-Joggobot, a small quad-rotored robot, will fly in front of you as you run to set the pace and keep you motivated','2012-06-03 15:45:22','2012-06-01 22:17:00','N'),
 (1105,'CAN SPACEX BUILD THE PICK-UP TRUCKS OF SPACE?','CAN SPACEX BUILD THE PICK-UP TRUCKS OF SPACE?-The Dragon space freighter is a slick piece of engineering, but still has a fair way to go','2012-06-03 15:45:22','2012-06-01 16:36:00','N'),
 (1106,'UAVS FLY WIRELESS POWER TO REMOTE LOCATIONS','UAVS FLY WIRELESS POWER TO REMOTE LOCATIONS-Quadrotor UAVs could be used as flying wireless batteries to charge sensors in difficult-to-reach places','2012-06-03 15:45:22','2012-06-01 16:26:00','N'),
 (1107,'WHAT INSECT SOCIETIES TELL US ABOUT CELLS','WHAT INSECT SOCIETIES TELL US ABOUT CELLS-Could the destiny of social insects and human cells be controlled by the same mechanisms, ask','2012-06-03 15:45:22','2012-06-01 12:30:00','N'),
 (1108,'CONQUERING CANCER BY THWARTING TUMORS IMMUNE SHIELD','CONQUERING CANCER BY THWARTING TUMORS IMMUNE SHIELD-Two clinical trials show promise for drugs that block cancers ability to hide','2012-06-03 15:45:24','2012-06-02 10:30:01','N'),
 (1109,'HIDE-AND-SEEK GOES VIRTUAL','HIDE-AND-SEEK GOES VIRTUAL-Computer simulations reveal surprising ways people conceal and discover objects','2012-06-03 15:45:24','2012-06-02 03:36:06','N'),
 (1110,'U.S. STUDENTS FLOCK TO GRADUATE SCIENCE PROGRAMS','U.S. STUDENTS FLOCK TO GRADUATE SCIENCE PROGRAMS-New NSF report offers an upbeat view of the future U.S. scientific workforce','2012-06-03 15:45:24','2012-06-02 02:24:58','N'),
 (1111,'THERAPEUTIC ANTIBODY PIONEERS GET SPAINS TOP SCIENCE PRIZE','THERAPEUTIC ANTIBODY PIONEERS GET SPAINS TOP SCIENCE PRIZE-Immunologists Gregory Winter and Richard Lerner receive Prince of Asturias award','2012-06-03 15:45:24','2012-06-02 01:06:37','N'),
 (1112,'SCIENCESHOT: CLIMATE COMBAT COULD TURN SKY WHITE','SCIENCESHOT: CLIMATE COMBAT COULD TURN SKY WHITE-Adding tiny aerosols to atmosphere to curb global warming could brighten the heavens','2012-06-03 15:45:24','2012-06-02 00:00:39','N'),
 (1113,'PODCAST: SOCIAL INEQUALITY, A SILENT KILLER, AND THE ROOTS OF OLD PERSON SMELL','PODCAST: SOCIAL INEQUALITY, A SILENT KILLER, AND THE ROOTS OF OLD PERSON SMELL-An audio roundup of some of our favorite stories of the week','2012-06-03 15:45:24','2012-06-01 22:30:00','N'),
 (1114,'KAVLI FOUNDATION NAMES ITS 2012 PRIZE WINNERS','KAVLI FOUNDATION NAMES ITS 2012 PRIZE WINNERS-Seven scientists net honors','2012-06-03 15:45:24','2012-06-01 01:57:50','N'),
 (1115,'SCIENCESHOT: TURNING THE TABLES ON THE MATING GAME','SCIENCESHOT: TURNING THE TABLES ON THE MATING GAME-When males become scarce, female gobies shift from coy to competitive','2012-06-03 15:45:24','2012-06-01 01:48:11','N'),
 (1116,'SILENT KILLER MAY BE DISEASE OF THE AFFLUENT','SILENT KILLER MAY BE DISEASE OF THE AFFLUENT-Early exposure to microbes in Amazon foragers protects them from chronic inflammation that kills Westerners','2012-06-03 15:45:24','2012-06-01 01:25:23','N'),
 (1117,'TEXAS RESEARCH FUND WILL RE-REVIEW MD ANDERSON DRUG-DISCOVERY PROPOSAL','TEXAS RESEARCH FUND WILL RE-REVIEW MD ANDERSON DRUG-DISCOVERY PROPOSAL-CPRIT will take another look at controversial $20 million incubator grant','2012-06-03 15:45:24','2012-06-01 01:20:43','N'),
 (1118,'WHY IS THE SOLAR SYSTEM SO BIZARRE?','WHY IS THE SOLAR SYSTEM SO BIZARRE?-Enigmas such as Uranuss skewed magnetic field continue to bedevil planetary scientists','2012-06-03 15:45:24','2012-06-01 00:37:51','N'),
 (1119,'ROBOTIC REHAB HELPS PARALYZED RATS WALK AGAIN','ROBOTIC REHAB HELPS PARALYZED RATS WALK AGAIN-A combination of high-tech therapies shows promise for treating spinal injuries','2012-06-03 15:45:24','2012-06-01 00:36:00','N'),
 (1121,'SCIENCESHOT: ITS OFFICIAL, MEN ARE THE DIRTIER SEX','SCIENCESHOT: ITS OFFICIAL, MEN ARE THE DIRTIER SEX-Womens workplaces less germ-ridden than mens, according to microbial analysis of office equipment','2012-06-03 15:45:24','2012-05-31 03:35:00','N'),
 (1122,'SCIENCESHOT: SUPERVOLCANOES CAN HAVE A SHORT FUSE','SCIENCESHOT: SUPERVOLCANOES CAN HAVE A SHORT FUSE-Massive eruptions may occur with only a few hundred years warning','2012-06-03 15:45:24','2012-05-31 03:30:00','N'),
 (1123,'TUMOR BLOCKER MAY FIGHT FIBROSIS','TUMOR BLOCKER MAY FIGHT FIBROSIS-Pieces of anticancer drug prevent abnormal accumulation of connective tissue','2012-06-03 15:45:24','2012-05-31 00:30:00','N'),
 (1124,'THE MILKY WAYS OLDEST AND WISEST STARS','THE MILKY WAYS OLDEST AND WISEST STARS-Astronomer invents new technique to trace the origins of our galaxy','2012-06-03 15:45:24','2012-05-30 23:40:00','N'),
 (1125,'SCIENCESHOT: TAPPING THE TOMATOS SECRETS','SCIENCESHOT: TAPPING THE TOMATOS SECRETS-Genome reveals fruits history','2012-06-03 15:45:24','2012-05-30 23:30:00','N'),
 (1126,'WELSH PINE MARTEN SIGHTINGS PROBE','WELSH PINE MARTEN SIGHTINGS PROBE-Reported sightings of pine martens in Wales, where the animal was believed extinct, are being investigated.','2012-06-04 12:10:04','2012-06-02 15:40:10','N'),
 (1127,'HOLLYWOOD ALIENS ARE OUR OWN PROJECTIONS','HOLLYWOOD ALIENS ARE OUR OWN PROJECTIONS-Hollywood-invented aliens are an abysmal failure of imagination.','2012-06-04 12:10:19','2012-06-04 01:38:07','N'),
 (1128,'AMERICAN CANNIBAL APOCALYPSE?','AMERICAN CANNIBAL APOCALYPSE?-A recent spate of sensational cannibalism cases is making headlines -- but the hype doesnt mean zombies are among us.','2012-06-04 12:10:19','2012-06-04 00:32:31','N'),
 (1129,'PROMETHEUS EXPLORES THE NATURE OF HUMANITY','PROMETHEUS EXPLORES THE NATURE OF HUMANITY-As the prequel to the 1979 classic sci-fi horror Alien opens, director Ridley Scott asks some deep questions.','2012-06-04 12:10:19','2012-06-03 14:24:00','N'),
 (1130,'NEW SCIENTIST - ONLINE NEWS','NEW SCIENTIST - ONLINE NEWS-Tree rings formed in the 8th century record a peak in cosmic ray activity','2012-06-04 12:10:25','2012-06-03 22:30:00','N'),
 (1131,'SCIENCESHOT: WHAT STRUCK EARTH IN 775?','SCIENCESHOT: WHAT STRUCK EARTH IN 775?-Cosmic-ray blast pinned down to a single year, but cause remains mysterious','2012-06-04 12:10:32','2012-06-03 23:30:00','N'),
 (1132,'HOPE FOR THE PARALYSED?','HOPE FOR THE PARALYSED?-What does success in rats mean for people?','2012-06-04 12:12:04','2012-06-01 21:49:49','N'),
 (1133,'WHERE HAVE ALL THE HUMMINGBIRDS GONE?','WHERE HAVE ALL THE HUMMINGBIRDS GONE?-The glacier lily as it','2012-06-04 12:12:18','2012-06-02 08:47:17','N'),
 (1134,'AMELIA EARHART DISTRESS CALL DETAILS EMERGE','AMELIA EARHART DISTRESS CALL DETAILS EMERGE-Dozens of radio signals heard after her plane crashed add to evidence that the legendary aviator didnt just vanish.','2012-06-04 12:12:19','2012-06-02 03:30:00','N'),
 (1135,'WOULD NYCS BAN ON BIG SODAS REDUCE OBESITY?','WOULD NYCS BAN ON BIG SODAS REDUCE OBESITY?-Experts say it might not be a solution on its own.','2012-06-04 12:12:19','2012-06-01 21:37:00','N'),
 (1136,'MAN EATS HEART OF ROOMMATE: DNEWS NUGGE','MAN EATS HEART OF ROOMMATE: DNEWS NUGGE-How many human incidents need to happen before we believe in the Zombie Apocalypse?','2012-06-04 12:12:19','2012-06-01 20:32:04','N'),
 (1137,'DARK MATTER, DARK ENERGY, DARK… MAGNETISM?','DARK MATTER, DARK ENERGY, DARK… MAGNETISM?-There is a new suspect in the search for the mysterious force ripping apart the cosmos, says','2012-06-04 18:24:11','2012-06-04 12:30:00','N'),
 (1138,'VAST COSMIC EVENT LEAVES RECORD IN ANCIENT TREES','VAST COSMIC EVENT LEAVES RECORD IN ANCIENT TREES-Tree rings formed in the 8th century record a peak in cosmic ray activity','2012-06-04 18:24:11','2012-06-03 22:30:00','N'),
 (1140,'DEAD CAT MADE INTO HELICOPTER: DNEWS NUGGETS','DEAD CAT MADE INTO HELICOPTER: DNEWS NUGGETS-A Dutch artist converts his dead pet into a helicopter.','2012-06-04 18:29:34','2012-06-04 17:37:36','N'),
 (1141,'WHO OWNS ASTEROIDS OR THE MOON?','WHO OWNS ASTEROIDS OR THE MOON?-Plans to mine minerals on celestial bodies could violate many aspects of international space law, says','2012-06-04 18:29:40','2012-06-04 14:30:00','N'),
 (1142,'HOW TINY INSECTS SURVIVE THE RAIN','HOW TINY INSECTS SURVIVE THE RAIN-Mosquitoes are so tiny that they become combined with raindrops as they fall and are left unharmed, footage reveals.','2012-06-05 22:06:05','2012-06-05 00:43:46','N'),
 (1143,'STOCKHOLM: BIRTH OF THE GREEN GENERATION','STOCKHOLM: BIRTH OF THE GREEN GENERATION-The 1972 gathering that put the environment on the global map','2012-06-05 22:06:05','2012-06-05 02:30:00','N'),
 (1144,'FOR KIDS: DNA TAKES NOTES','FOR KIDS: DNA TAKES NOTES-Engineers develop a computer memory system based on living cells','2012-06-05 22:06:07','2012-06-05 21:57:05','N'),
 (1145,'HOW A MOSQUITO SURVIVES A RAINDROP HIT','HOW A MOSQUITO SURVIVES A RAINDROP HIT-Lightweight insects can ride water droplets, as long as they separate in time','2012-06-05 22:06:07','2012-06-05 00:39:04','N'),
 (1146,'ANCIENT BIRDS WIPED OUT HUGE INSECTS','ANCIENT BIRDS WIPED OUT HUGE INSECTS-Aerial competition trumped the advantage of extra atmospheric oxygen','2012-06-05 22:06:07','2012-06-05 00:31:49','N'),
 (1147,'REIGN OF THE GIANT INSECTS ENDED WITH THE EVOLUTION OF BIRDS','REIGN OF THE GIANT INSECTS ENDED WITH THE EVOLUTION OF BIRDS-Giant insects ruled the prehistoric skies during periods when Earth','2012-06-05 22:06:16','2012-06-05 01:27:57','N'),
 (1148,'MOSQUITOES FLY IN RAIN THANKS TO LOW MASS','MOSQUITOES FLY IN RAIN THANKS TO LOW MASS-Even rain can','2012-06-05 22:06:16','2012-06-05 01:25:55','N'),
 (1149,'SEARCH ENGINE FOR SOCIAL NETWORKS BASED ON THE BEHAVIOR OF ANTS','SEARCH ENGINE FOR SOCIAL NETWORKS BASED ON THE BEHAVIOR OF ANTS-Researchers are developing an algorithm, based on','2012-06-05 22:06:16','2012-06-04 18:58:28','N'),
 (1150,'THE STAGES OF THE TRANSIT OF VENUS EXPLAINED','THE STAGES OF THE TRANSIT OF VENUS EXPLAINED-The marathon event lasts nearly seven hours and includes a handful of key events.','2012-06-05 22:06:19','2012-06-05 18:17:00','N'),
 (1151,'IS FACEBOOK ABOUT TO LURE CHILDREN?','IS FACEBOOK ABOUT TO LURE CHILDREN?-Facebook is looking into technology that could let children under 13 register for the site.','2012-06-05 22:06:19','2012-06-05 18:12:21','N'),
 (1152,'A HISTORY OF REAL ZOMBIES','A HISTORY OF REAL ZOMBIES-They seem to be everywhere these days. But where do zombies come from?','2012-06-05 22:06:19','2012-06-05 04:35:42','N'),
 (1153,'WHY SOME PEOPLE BLAME THEMSELVES FOR EVERYTHING','WHY SOME PEOPLE BLAME THEMSELVES FOR EVERYTHING-Feel like you are always to blame? Its not your fault.','2012-06-05 22:06:19','2012-06-05 03:54:00','N'),
 (1154,'ZOMBIE PRANKSTER ALMOST SHOT: DNEWS NUGGETS','ZOMBIE PRANKSTER ALMOST SHOT: DNEWS NUGGETS-Man in a zombie costume was nearly shot in Miami.','2012-06-05 22:06:19','2012-06-05 01:51:24','N'),
 (1155,'EARLIEST KNOWN HUMAN RELATIVES CAME FROM ASIA','EARLIEST KNOWN HUMAN RELATIVES CAME FROM ASIA-The group of animals that gave rise to humans, primates and monkeys had Asian origins.','2012-06-05 22:06:19','2012-06-05 00:30:00','N'),
 (1156,'LARGEST FLEXIBLE DISPLAY DEBUTS','LARGEST FLEXIBLE DISPLAY DEBUTS-The display is part of a Department of Defense project to make tougher screens for the battlefield.','2012-06-05 22:06:19','2012-06-04 23:53:06','N'),
 (1157,'CAN YOU EXERCISE TOO MUCH?','CAN YOU EXERCISE TOO MUCH?-Exercising casually may be healthier in the long run than cranking out marathons, say researchers.','2012-06-05 22:06:19','2012-06-04 23:36:20','N'),
 (1158,'MAD MAGAZINE FLASHBACK: THE MORALITY CRISIS','MAD MAGAZINE FLASHBACK: THE MORALITY CRISIS-The cycle of crises may be predictable, but those pertaining to sex tend to stick around...for almost 40 years?','2012-06-05 22:06:19','2012-06-04 22:44:14','N'),
 (1159,'STARFISH BEACH THEMSELVES: DNEWS NUGGETS','STARFISH BEACH THEMSELVES: DNEWS NUGGETS-Normally its whales or dolphins that beach themselves. This time, its starfish.','2012-06-05 22:06:19','2012-06-04 22:26:48','N'),
 (1160,'INVENTOR WINS $500,000 PRIZE: DNEWS NUGGETS','INVENTOR WINS $500,000 PRIZE: DNEWS NUGGETS-Stephen Quake, who has more than 80 patents and four companies to his name, has won the Lemelson-MIT prize.','2012-06-05 22:06:19','2012-06-04 21:14:00','N'),
 (1161,'HOW TO PHOTOGRAPH THE TRANSIT OF VENUS SAFELY','HOW TO PHOTOGRAPH THE TRANSIT OF VENUS SAFELY-The next time this historic event will happen is 105 years from now.','2012-06-05 22:06:19','2012-06-04 20:36:00','N'),
 (1162,'CLIPBOARD IS ABOUT GETTING THINGS DONE','CLIPBOARD IS ABOUT GETTING THINGS DONE-A new Web-clipping service boosts productivity.','2012-06-05 22:06:19','2012-06-04 20:17:41','N'),
 (1163,'DUAL-ZONE MATTRESS PAD KEEPS YOU COOL OR WARM','DUAL-ZONE MATTRESS PAD KEEPS YOU COOL OR WARM-A wirelessly controlled dual-zone mattress pad keeps you and your bedmate comfortable.','2012-06-05 22:06:19','2012-06-04 20:03:52','N'),
 (1164,'DIET GLASSES FOOL WEARERS INTO EATING LESS','DIET GLASSES FOOL WEARERS INTO EATING LESS-Augmented reality goggles trick eaters into feeling more satisfied with their meals.','2012-06-05 22:06:19','2012-06-04 19:57:00','N'),
 (1165,'GENERATION GREEN SPENDERS','GENERATION GREEN SPENDERS-Young adults care that their consumer habits are green.','2012-06-05 22:06:19','2012-06-04 19:21:10','N'),
 (1166,'RESTAURANTEUR FINDS A WAY TO CANCEL NOISE','RESTAURANTEUR FINDS A WAY TO CANCEL NOISE-A sound engineer developed a way to control noise levels in his own restaurant.','2012-06-05 22:06:19','2012-06-04 18:40:00','N'),
 (1167,'FLAME VIRUS HIJACKED WINDOWS LAST LINE OF DEFENCE','FLAME VIRUS HIJACKED WINDOWS LAST LINE OF DEFENCE-If we cant trust Microsofts update mechanism to protect our computers from threats, what can we trust?','2012-06-05 22:06:24','2012-06-05 17:22:00','N'),
 (1168,'DO SOLO BLACK HOLES ROAM THE UNIVERSE?','DO SOLO BLACK HOLES ROAM THE UNIVERSE?-New observations provide strongest evidence to date that black holes can be kicked out of their home galaxies','2012-06-05 22:06:26','2012-06-05 21:32:00','N'),
 (1169,'SPY TELESCOPES COULD ADVANCE U.S. DARK ENERGY MISSION','SPY TELESCOPES COULD ADVANCE U.S. DARK ENERGY MISSION-The two telescopes were designed to gaze down upon Earth from space to collect...','2012-06-05 22:06:26','2012-06-05 03:26:42','N'),
 (1170,'RAINDROPS DONT SWAT DOWN MOSQUITOES','RAINDROPS DONT SWAT DOWN MOSQUITOES-The annoying insects tough body lets it survive midair collisions with much bigger raindrops and keep on flying','2012-06-05 22:06:26','2012-06-05 01:40:00','N'),
 (1171,'AN ASIAN ORIGIN FOR HUMAN ANCESTORS?','AN ASIAN ORIGIN FOR HUMAN ANCESTORS?-Myanmar fossil suggests our earliest predecessors may not have come from Africa','2012-06-05 22:06:26','2012-06-05 01:34:00','N'),
 (1172,'WHERE HAVE THE HAWK-SIZED INSECTS GONE?','WHERE HAVE THE HAWK-SIZED INSECTS GONE?-Study suggests that as bird flight improved, the size of their insect prey dropped considerably','2012-06-05 22:06:26','2012-06-05 01:32:00','N'),
 (1173,'WHITE HOUSE PETITION URGING OPEN ACCESS REACHES 25,000 SIGNERS','WHITE HOUSE PETITION URGING OPEN ACCESS REACHES 25,000 SIGNERS-Threshold reached for triggering official response on expanding public access policies','2012-06-05 22:06:26','2012-06-05 00:36:36','N'),
 (1174,'BP WINS RIGHT TO SCIENTISTS OIL SPILL E-MAILS','BP WINS RIGHT TO SCIENTISTS OIL SPILL E-MAILS-Researchers hand over 3000 internal documents','2012-06-05 22:06:27','2012-06-05 00:09:34','N'),
 (1175,'FOR KIDS: MAMMALS FEEL THE HEAT','FOR KIDS: MAMMALS FEEL THE HEAT-Scientists predict some animals won’t be able to keep up with Earth’s increasing temperatures','2012-06-06 00:07:06','2012-06-05 23:54:51','N'),
 (1176,'JAPAN AND THE UNITED STATES EYE COOPERATION IN DISASTER RESEARCH','JAPAN AND THE UNITED STATES EYE COOPERATION IN DISASTER RESEARCH-NSF chief Subra Suresh visits Tokyo','2012-06-06 00:07:25','2012-06-06 00:10:22','N');
/*!40000 ALTER TABLE `sciencealerts` ENABLE KEYS */;


--
-- Definition of table `smssender`
--

DROP TABLE IF EXISTS `smssender`;
CREATE TABLE `smssender` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Address` varchar(100) NOT NULL,
  `UserName` varchar(100) DEFAULT NULL,
  `IsReg` varchar(1) NOT NULL DEFAULT 'N',
  `Marks` bigint(20) DEFAULT NULL,
  `IsSchedularActive` varchar(1) NOT NULL DEFAULT 'Y',
  `JoinedDate` datetime DEFAULT NULL,
  `LastActiveTime` datetime DEFAULT NULL,
  `IsActive` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smssender`
--

/*!40000 ALTER TABLE `smssender` DISABLE KEYS */;
/*!40000 ALTER TABLE `smssender` ENABLE KEYS */;


--
-- Definition of table `smstransaction`
--

DROP TABLE IF EXISTS `smstransaction`;
CREATE TABLE `smstransaction` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ReceiverAddress` varchar(100) DEFAULT NULL,
  `ReceiverSms` varchar(160) DEFAULT NULL,
  `TransactionTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_ReceiverId` (`ReceiverAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=767 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smstransaction`
--

/*!40000 ALTER TABLE `smstransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `smstransaction` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
