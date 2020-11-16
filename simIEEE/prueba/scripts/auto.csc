atget id id
vec autos 6
vec conectados 6

for i 1 6 1
	vset 0 conectados $i 
end


llaves priv pub

data m hola $id $pub

loop
send $m 
wait 200
read r
rdata $r type idsensor info 
cprint $r
if($type==hola)
    	vget x conectados $idsensor
	if($x==0)
    	 	secreto $info pepe
    		vset 1 conectados $idsensor
		vset $sec autos $idsensor		
	end
		getpos x
		cifrar $x $idsensor
		data m pos $id $cifrado
		send $m $idsensor
end
if($type==pos)
	vget x conectados $idsensor
	if($x==1)
		descifrar $info $idsensor
	end
end

delay 1000