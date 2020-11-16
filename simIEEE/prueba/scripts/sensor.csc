
atget id id
vec autos 5
vec conectados 5
llaves priv pub
loop
atnd x

wait 

read m

rdata $m type idA info 

if($type==hola)

    data r holaResp $id $pub
    
    send $r $idA
    secreto $info pepe2
    vset $sec autos $idA
end
if($type==pos)
    vget s autos $idA
    descifrar $info
end
cprint $info $idA