

begin = 1
end = 12

partcount = dict()
partmvmt = dict()
gpartcount = dict()
gpartmvmt = dict()

rankcount = 3600
timesteps = []

for i in range(begin,end+1):
    timesteps.append(i*100)



#fout1 = open("heatmap-partcount"+str(begin)+"-"+str(end)+".csv",'a')
#fout3 = open("heatmap-partmvmt"+str(begin)+"-"+str(end)+".csv",'a')
for step in range(begin,end):
    fin1 = open("partcount"+str(step)+"-"+str(step+1)+".csv",'r')
    fin2 = open("gpartcount"+str(step)+"-"+str(step+1)+".csv",'r')
    fin3 = open("partmvmt"+str(step)+"-"+str(step+1)+".csv",'r')
    fin4 = open("gpartmvmt"+str(step)+"-"+str(step+1)+".csv",'r')

    for line in fin1:
        line = line.rstrip()
        ls = line.split(",")
        if(len(ls) == 3):
            partcount[(ls[0],ls[1])] = ls[2]

    for line in fin2:
        line = line.rstrip()
        ls = line.split(",")
        if(len(ls) == 3):
            gpartcount[(ls[0],ls[1])] = ls[2]

    for line in fin3:
        line = line.rstrip()
        ls = line.split(",")
        if(len(ls) == 4):
            partmvmt[(ls[0],ls[1],str(int(ls[2])-99))] = ls[3]

    for line in fin4:
        line = line.rstrip()
        ls = line.split(",")
        if(len(ls) == 4):
            gpartmvmt[(ls[0],ls[1],str(int(ls[2])-99))] = ls[3]


print(len(partcount.keys()))
print(len(gpartcount.keys()))
print(timesteps)
#for (i,j) in sorted(partcount.keys()):
    


"""for i in range(rankcount):
    fout1.write(str(i))
    fout3.write(str(i))
    for ts in sorted(timesteps):
        fout1.write(","+str(partcount[(str(i),str(ts))]))
        fout3.write(","+str(gpartcount[(str(i),str(ts))]))

    fout1.write("\n")
    fout3.write("\n")


for k in timesteps:
    fout2 = open("heatmap-partmvmt"+str(begin)+"-"+str(end)+"timestep"+str(k)+".csv",'a')
    fout4 = open("heatmap-partmvmt"+str(begin)+"-"+str(end)+"timestep"+str(k)+".csv",'a')
    for i in range(rankcount):
        for j in range(rankcount):
            if(i == 0 and j == 0):
                fout2.write(partmvmt.get((str(i),str(j),str(k)),"0"))
                fout4.write(gpartmvmt.get((str(i),str(j),str(k)),"0"))
            else:
                fout2.write(","+partmvmt.get((str(i),str(j),str(k)),"0"))
                fout4.write(","+gpartmvmt.get((str(i),str(j),str(k)),"0"))
        fout2.write("\n")
        fout4.write("\n")
    fout2.close()
    fout4.close()

fout1.close()
fout3.close()"""

        
        
