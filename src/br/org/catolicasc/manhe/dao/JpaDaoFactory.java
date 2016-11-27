package br.org.catolicasc.manhe.dao;

public class JpaDaoFactory {

	public static JpaDaoFactory instance = new JpaDaoFactory();
	
	private UserDao userDao;
	private MedicalRecommendationDao medicalRecommendationDao;
	private LayetteDao layetteDao;
	private MoodDao moodDao;
	private ScheduleDao scheduleDao;
	private MoodHistoryDao moodHistoryDao;
	
	private JpaDaoFactory() {}
		
	public static JpaDaoFactory getInstance(){
		return instance;
	}
	
	
	public UserDao getUserDao(){
		if(this.userDao == null)
			this.userDao = new UserDao();
		return this.userDao;
	}

	public MedicalRecommendationDao getMedicalRecommendationDao(){
		if(this.medicalRecommendationDao == null)
			this.medicalRecommendationDao = new MedicalRecommendationDao();
		return this.medicalRecommendationDao;
	}
	
	public LayetteDao getLayetteDao(){
		if(this.layetteDao == null)
			this.layetteDao = new LayetteDao();
		
		return this.layetteDao;
	}
	
	public MoodDao getMoodDao(){
		if(this.moodDao == null)
			this.moodDao = new MoodDao();
		
		return this.moodDao;
	}
	
	public ScheduleDao getScheduleDao(){
		if(this.scheduleDao == null)
			this.scheduleDao = new ScheduleDao();
		
		return this.scheduleDao;
	}
	
	public MoodHistoryDao getMoodHistoryDao(){
		if(this.moodHistoryDao == null)
			this.moodHistoryDao = new MoodHistoryDao();
		
		return this.moodHistoryDao;
	}
}
