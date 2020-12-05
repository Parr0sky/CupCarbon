loop
llaves priv pub
data m hola $pub
send $m
wait
read r
rdata $r pep publica

secreto $publica t
cprint $sec

cifrar hola
cprint $cifrado

descifrar $cifrado
cprint $claro
wait