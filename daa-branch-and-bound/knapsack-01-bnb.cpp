#include <iostream>
using namespace ::std;


void print(int*p,int*w,int n){

    cout<<"\n";
    for (int i=0;i<=n;i++){
        if(i==0){
            cout<<"i= ";
        }
        else{
            cout<<i<<",";
        }
    }

    cout<<"\n";

    for (int i=0;i<=n;i++){
        if(i==0){
            cout<<"p= ";
        }
        else{
            cout<<p[i]<<",";
        }
    }

    cout<<"\n";

    for (int i=0;i<=n;i++){
        if(i==0){
            cout<<"w= ";
        }
        else{
            cout<<w[i]<<",";
        }
    }

    cout<<"\n";
    cout<<endl;
}

void bubblesort(int*p,int*w,int n){
    float*d=new float[n+1];
    for (int i=1;i<n;i++){
        d[i]=p[i] / (float)(w[i]);
    }

    for (int i=0;i<(n-1);i++){
        for (int j=1;j<=((n-1)-i);j++){
            if(d[j]<d[j+1]){
                swap(p[j],p[j+1]);
                swap(d[j],d[j+1]);
                swap(w[j],w[j+1]);
            };
        }
    }

    cout<<"Sorted using bubble sort:\n";
    print(p,w,n);
}

void printsol(int cap,int*p,int*sol,int*w,int n){
    int m=cap;
    int cp=0;

    for (int i=1;i<=n;i++){
        if(sol[i]){
            cp += p[i];
        }
    }
    cout<<"\nPROFIT: "<<cp<<endl;

    for (int i=1;i<=n;i++){
        if(sol[i] && w[i]<=cap){
            cap-=w[i];
        }
    }
    cout<<"WEIGHT: "<<m-cap<<endl;
}

float findLB(int o,int tORf,int*p,int cap,int*sol,int*w,int n){
    sol[o]=tORf;
    float LB=0;

    for (int i=1;i<=n;i++){
        if(((i<=o) && sol[i]) || (i > o)){
            if(w[i] > cap){
                LB -= (cap / (float)(w[i]))* p[i];
                cap=0;
                break;
            }
            else{
                LB -= p[i];
                cap -= w[i];
            }
        }
    }

    sol[o]=0;
    return LB;
}

int findUB(int o,int tORf,int*p,int cap,int*sol,int*w,int n){
    sol[o]=tORf;
    int UB=0;

    for (int i=1;i<=n;i++){
        if(((i<=o) && sol[i]) || (i > o)){        
            if(w[i] > cap){
                break;
            }
            else{
                UB -= p[i];
                cap -= w[i];
            }
        }
    }

    sol[o]=0;
    return UB;
}

int*BnB(int*p,int o,int*w,int*sol,int m,int n){

    if(o==(n+1)){
        return sol;
    }

    float lb1=findLB(o,1,p,m,sol,w,n);
    int ub1=findUB(o,1,p,m,sol,w,n);

    float lb0=findLB(o,0,p,m,sol,w,n);
    int ub0=findUB(o,0,p,m,sol,w,n);

    if(lb1<lb0){    
        sol[o]=1;
    }
    else if(lb1==lb0){
        if(ub1<ub0){
            sol[o]=1;
        }
        else{
            sol[o]=0;
        }
    }
    else{
        sol[o]=0;
    }

    return BnB(p,o+1,w,sol,m,n);
}

void knapsackBnB(){
    int n;
    int m;

    // cout<<"n : ";
    // cin>>n;

    // int* w=new int[n+1];
    // int* p=new int[n+1];

    // for(int i=1;i<=n;i++)
    //     cin>>p[i]>>w[i];
    // }

    // cout<<"cap: ";
    // cin>>m;


    int p[5]={0,10,10,12,18};
    m=15;
    n=4;
    int w[5]={0,2,4,6,9};

    cout<<"\nCap of KNAPSACK: "<<m<<"\n";

    bubblesort(p,w,n);
    int*s=new int[n+1];
    int*sol=BnB(p,1,w,s,m,n);

    printsol(m,p,sol,w,n);

    for (int o=1;o<=n;o++){
        cout<<sol[o]<<",";
    }
    cout<<endl;
}

int main(){
    knapsackBnB();
    return 0;
}