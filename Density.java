package FDM1D;

// density subclass extends Variable super class
// rachaitha: pkc

class Density extends Variable{

//...............constructor.............
Density(int[] Ns){
Nx=Ns[0];
Ny=Ns[1];
Nz=Ns[2];
value= new double[Nx][Ny][Nz];
}// x constructor


//...............method..................
void setDensity(Grid grid1,double DF_){
int i,j,k;
DF=DF_;
Nc=1;
conNodes=new int[Nc][4];
cons=new double[Nc][2];
conNodes[0][0]=0;conNodes[0][1]=0;conNodes[0][2]=0;conNodes[0][3]=0;
cons[0][0]=1;
for(i=0;i<Nx;i++)
for(j=0;j<Ny;j++)
for(k=0;k<Nz;k++)
value[i][j][k]=1-0.3146*grid1.x[i];
}// x method setDensity

void printDensity(){
int i,j,k;
System.out.printf("\n  \nDensity Data");
System.out.printf("\n  Dimensions  %d %d %d",Nx,Ny,Nz);
System.out.printf("\n  DF=%+-8.4f",DF);
System.out.printf("\n      i    j    k\tDensity(ND)");
for(i=0;i<Nx;i++)
for(j=0;j<Ny;j++)
for(k=0;k<Nz;k++)
System.out.printf("\n  %5d%5d%5d\t%+-8.4f",i,j,k,value[i][j][k]);
}// x method printDensity

void dodtf(Velocityx ux1, Area area1){
int i,j,k;
dt= new double[Nx][Ny][Nz];
//System.out.printf("\n  Time rate of change of Density Forward difference");
//System.out.printf("\n      i\t    j\t    k\t    d/dt");
for(i=0;i<Nx;i++)
for(j=0;j<Ny;j++)
for(k=0;k<Nz;k++){
dt[i][j][k]=-value[i][j][k]*ux1.fdx[i][j][k]-ux1.value[i][j][k]*fdx[i][j][k]-value[i][j][k]*ux1.value[i][j][k]*area1.lnfdx[i][j][k];
//System.out.printf("\n  %5d\t%5d\t%5d\t%8.4f",i,j,k,dt[i][j][k]);
}
}// x dodtf

void dodtb(Velocityx ux2, Area area1){
int i,j,k;
dt= new double[Nx][Ny][Nz];
//System.out.printf("\n  Time rate of change of Density Forward difference");
//System.out.printf("\n      i\t    j\t    k\t    d/dt");
for(i=0;i<Nx;i++)
for(j=0;j<Ny;j++)
for(k=0;k<Nz;k++){
dt[i][j][k]=-value[i][j][k]*ux2.bdx[i][j][k]-ux2.value[i][j][k]*bdx[i][j][k]-value[i][j][k]*ux2.value[i][j][k]*area1.lnbdx[i][j][k];
//System.out.printf("\n  %5d\t%5d\t%5d\t%8.4f",i,j,k,dt[i][j][k]);
}
}// x dodtb

}// x class Density