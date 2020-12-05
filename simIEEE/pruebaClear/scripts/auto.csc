atget id id
vec conectados 6

for i 1 6 1
	vset 0 conectados $i 
end
loop


data m hola $id
send $m


wait 200
read r
rdata $r type idsensor

if($type==hola)
    	vget x conectados $idsensor
	if($x==0)
		vset 1 conectados $idsensor
	else
		getpos x
		data m posSC $id $x
		send $m $idsensor
	end
end
		
if($type==posSC)
	vget x conectados $idsensor
	if($x==1)
		
		cprint $info $type
	end
end

delay 1000
