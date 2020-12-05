atget id id
vec autos 30
vec conectados 30

for i 1 30 1
	vset 0 conectados $i 
end


llaves priv pub

loop
atnd n

data m hola $id $pub
send $m 
wait 200
read r
rdata $r type idsensor info 
if($type==hola)
    	vget x conectados $idsensor
	if($x==0)
    	 	secreto $info pepe
    		vset 1 conectados $idsensor
		vset $sec autos $idsensor
		data m holaResp $id $pub
		send $m $idsensor		
	end
		getpos x
		if($n<2)
			
			cifrar $x $idsensor
			data m pos $id $cifrado
			send $m $idsensor
		else

			data m posSC $id $x
			send $m $idsensor
		end
end
if($type==holaResp)
		secreto $info pepe
    		vset 1 conectados $idsensor
		vset $sec autos $idsensor
	
end
if($type==pos)
	vget x conectados $idsensor
	if($x==1)
		
		descifrar $info $idsensor
		cprint $claro $idsensor
	end
end
if($type==posSC)
	vget x conectados $idsensor
	if($x==1)
		
		cprint $info $type
	end
end
delay 1000