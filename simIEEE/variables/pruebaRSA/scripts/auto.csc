atget id id
vec autos 30
vec conectados 30

for i 1 30 1
	vset 0 conectados $i 
end


llavesr priv pub
cprint $priv
cprint $pub
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
    		vset 1 conectados $idsensor
		vset $info autos $idsensor
		data m holaResp $id $pub
		send $m $idsensor		
	end
		getpos x
		if($n<2)
			
			rsae $x $idsensor
			data m pos $id $cifrado
			send $m $idsensor
		else

			data m posSC $id $x
			send $m $idsensor
		end
end
if($type==holaResp)
		
    		vset 1 conectados $idsensor
		vset $info autos $idsensor
	
end
if($type==pos)
	vget x conectados $idsensor
	if($x==1)
		
		rsad $info $idsensor
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