public class NBody {

	/**
	Return a double corresponding to the radius of the universe in that file
	*/
	public static double readRadius(String dir)
	{
		In in = new In(dir);

		/* Every time you call a read method from the In class,
		 * it reads the next thing from the file, assuming it is
		 * of the specified type. */

		int number = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	/**
	Return an array of Planets corresponding to the planets in the file
	*/
	public static Planet[] readPlanets(String dir)
	{
		In in = new In(dir);
		int number = in.readInt();
		double radius = in.readDouble();
		Planet[] p = new Planet[number];

		for(int i = 0; i < number; i ++)
		{
			double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xP, yP, xV, yV, m, img);

			/**
			p[i] = new Planet(0, 0, 0, 0, 0, "/");
			p[i].xxPos = in.readDouble();
			p[i].yyPos = in.readDouble();
			p[i].xxVel = in.readDouble();
			p[i].yyVel = in.readDouble();
			p[i].mass = in.readDouble();
			p[i].imgFileName = in.readString();
			*/
		}
		return p;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        
        /** 
        Draw the background 
        */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /** 
        Draw planets 
        */
        for (Planet p : planets) {
        	p.draw();
        }


        /**
        Animation
        */

		//Enables double buffering for smooth animation rendering and prevent it from flickering
        StdDraw.enableDoubleBuffering();

        /**
        Set up a loop to loop until time variable reaches T
        */
        for (double t = 0; t <= T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            
            /**
            Calculate the net forces for every planet
            */
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /**
            Update positions and velocities of each planet
            */
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /**
            Draw the background
            */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            
            /**
            Draw all planets
            */
            for (Planet p : planets) {
                p.draw();
            }
            
            StdDraw.show();
            StdDraw.pause(10);

        }
        
        /**
        Print out the final state of the universe when time reaches T
        */
 		StdOut.printf("%d\n", planets.length); 
 		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
				planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
				planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		} 
	}
}