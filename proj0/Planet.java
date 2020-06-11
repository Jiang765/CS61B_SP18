public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private final static double G = 6.67e-11;

	/**
	Constructor to initialize an instance of the Planet class with given parameters
	@param xP: current x position
	@param yP: current y position
	@param xV: current velocity in the x direction
	@param yV: current velocity in the y direction
	@param m: mass
	@param img: path to an image file that depicts the planet
	*/
	public Planet(double xP, double yP, double xV, double yV, double m, String img)
	{
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	/**
x	*/
	public Planet(Planet p)
	{
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/**
	Calculates the distance between two Planets. 
	*/
	public double calcDistance(Planet p)
	{
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
    Calculates the force exerted on this Planet by the given Planet
    */
    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        double F =  G * mass * p.mass / (r * r);
        return F;
    }

	/**
	Total force in x direction
	*/
    public double calcForceExertedByX(Planet p)
    {
    	double dx = p.xxPos - this.xxPos;
		double r = calcDistance(p);
    	double F = G * this.mass * p.mass / r / r;
    	return F * dx / r;
    }

	/**
	Total force in y direction
	*/
	public double calcForceExertedByY(Planet p)
    {
    	double dy = p.yyPos - this.yyPos;
		double r = calcDistance(p);
    	double F = G * this.mass * p.mass / r / r;
    	return F * dy / r;
    }

	/**
	Total force in x direction from all planets
	*/
    public double calcNetForceExertedByX(Planet[] p)
    {
    	double td = 0;
    	for(Planet d : p)
		{
			if(this.equals(d))
				continue;
			td += this.calcForceExertedByX(d);
		}
		return td;
    }

   	/**
	Total force in y direction from all planets
	*/
	public double calcNetForceExertedByY(Planet[] p)
	{
    	double td = 0;
    	for(Planet d : p)
		{
			if(this.equals(d))
				continue;
			td += this.calcForceExertedByY(d);
		}
		return td;
	}

	/**  
	Update the planet’s position and velocity instance variables
	*/
	public void update(double dt, double fX, double fY)
	{
		double ax = fX / this.mass;
		double ay = fY / this.mass;

		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw()
	{
 		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}