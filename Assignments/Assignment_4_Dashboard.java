public class Dashboard implements Comparable<Dashboard> {
    private int odometer;
    private int speedometer;
    private boolean checkEngine;
    
    public Dashboard(){
        this.odometer = 0;
        this.speedometer = 0;
        this.checkEngine = false;
    }
    
    public Dashboard(int milesTravelled, int speed){
        this.checkEngine = false;
        if(milesTravelled <= 99999 && milesTravelled>=0){
            this.odometer = milesTravelled;
        }else{
            this.odometer = 0;
            this.checkEngine = true;
        }
        if(speed <= 100 && speed>=0){
            this.speedometer = speed;
        }else{
            this.speedometer = 0;
            this.checkEngine = true;
        }
    }

    
    public String toString(){
        String ret = "";
        ret += "Speedometer: "+speedometer+" MPH\n";
        ret+= "Odometer: ";
        if(odometer < 10000){
            ret+= "0";
        }
        if(odometer < 1000){
            ret += "0";
        }
        if(odometer < 100){
            ret += "0";
        }
        if(odometer < 10){
            ret += "0";
        }
        ret += odometer+"\n";
        ret+= "Check Engine: ";
        if(checkEngine){
            ret+= "On";
        }else{
            ret += "Off";
        }
        return ret;
    }
    
    public void accelerate(){
        this.speedometer++;
        if(this.speedometer > 100){
            this.speedometer = 0;
            this.checkEngine = true;
        }
    }
    
    public void drive(int n){
        double milesPerMinute = (double)this.speedometer/60;
         double totalMiles = milesPerMinute * n;
        this.odometer += (int)totalMiles;
        if(this.odometer > 99999){
            this.odometer = 0;
            this.checkEngine = true;
        }
    }
    
    public int compareTo(Dashboard other)
    {
    	if(this.odometer < other.odometer)
    	{
    		return -1;
    	}
    	else if(this.odometer > other.odometer)
    	{
    		return 1;
    	}
    	else if(this.odometer == other.odometer)
    	{
    		if(this.speedometer < other.speedometer)
    		{
    			return -1;
    		}
    		else if(this.speedometer > other.speedometer)
    		{
    			return 1;
    		}
    		else if(this.speedometer == other.speedometer)
    		{
    			if(!(this.checkEngine) && other.checkEngine)
    			{
    				return -1;
    			}
    			else if(this.checkEngine && !(other.checkEngine))
    			{
    				return 1;
    			}
    		}
    	}
    	
    	return 0;
    }
    
    public String race(Dashboard other, int acc1, int acc2)
    {
    	boolean carOneDidNotFinish = false;
    	boolean carTwoDidNotFinish = false;
    	String ret = "";
    	
    	for(int i = 0; i < acc1; i++)
    	{
    		this.accelerate();
    		if(checkEngine)
    		{
    			ret += "First car stalled out!\n";
    			carOneDidNotFinish = true;
    			break;
    		}
    	}
    	
    	for(int i = 0; i < acc2; i++)
    	{
    		other.accelerate();
    		if(other.checkEngine)
    		{
    			ret += "Second car stalled out!";
    			carTwoDidNotFinish = true;
    			break;
    		}
    	}
    	
    	ret += "\n" + this.toString() + "\n\n" + other.toString() + "\n\n";
    	
    	if((!(carOneDidNotFinish) && carTwoDidNotFinish) || this.speedometer > other.speedometer)
    	{
    		ret += "Car 1 has won the race!";
    	}
    	else if((carOneDidNotFinish && !(carTwoDidNotFinish)) || other.speedometer > this.speedometer)
    	{
    		ret += "Car 2 has won the race!";
    	}
    	else
    	{
    		ret += "It's a tie!";
    	}
    	
    	return ret;
    }
    
    public String difference(Dashboard other)
    {
    	String ret = "";
    	
    	if(this.odometer < other.odometer)
    	{
    		ret += "First car will need to drive for " + (int)(((double) (other.odometer - this.odometer) / this.speedometer) * 60) + " minutes to catch second car.";
    	}
    	else if(this.odometer > other.odometer)
    	{
    		ret += "Second car will need to drive for " + (int)(((double) (this.odometer - other.odometer) / other.speedometer) * 60) + " minutes to catch first car.";
    	}
    	return ret;
    }
}
