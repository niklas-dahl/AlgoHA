confirm () {
    read -r -n 1 -p "$1 [y/N] " response
    echo
    case $response in
        [yY][eE][sS]|[yY])
            true
            ;;
        *)
            false
            ;;
    esac
}

echo "AlgoHA Zip Creator :D (Benötigt 7zip Binary in PATH)"
echo
if confirm "Soll ein git-Update ausgeführt werden?"
then
  git pull
fi
echo
read -p "Um welche HA-Nummer geht es? " hanum
echo
echo ----------
7z a -r temp.zip "src/ha$hanum/"
echo ----------
echo
printf -v hanum "%02d" $hanum
mv temp.zip ~/Downloads/"algo_05_h$hanum.zip"
echo "*** Archiv in Downloads; Name: 'algo_05_h$hanum.zip' !"
read -n 1
