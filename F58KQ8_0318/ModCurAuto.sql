create or replace procedure aut_arnov1(szinbe in char, ert in int) is
begin
    open cur_a;
    loop
        fetch cur_a into a;
        exit when cur_a%notfound;
        update autok set ar=a.ar*(1+ert/100) where current of cur_a;
    end loop;
    close cur_a;
end;

begin

    aut_arnov1('piros', 10);
    
end;
