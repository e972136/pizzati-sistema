-- PROCEDURE: public.almacenar_vendedor(character varying, character varying, integer)

-- DROP PROCEDURE IF EXISTS public.almacenar_vendedor(character varying, character varying, integer);

CREATE OR REPLACE PROCEDURE public.almacenar_vendedor(
	nombre_in character varying,
	departamento_in character varying,
	INOUT rv_id_vendedor integer)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
    _conteo integer;
begin

    if nombre_in is NULL then
        rv_id_vendedor = 0;
        RAISE EXCEPTION 'Falta nombre';
        RETURN;
    end if;
    if nombre_in is NULL then
        rv_id_vendedor = 0;
        RAISE EXCEPTION 'Falta departamento';
        RETURN;
    end if;

    select count(*) into _conteo
        from vendedor
        where nombre = nombre_in and departamento = departamento_in;

    if(_conteo>0)then
        RAISE EXCEPTION 'Vendedor ya existe en ese departamento';
        rv_id_vendedor = 0;
        RETURN;
    else
        insert into vendedor(nombre,departamento)
            values(nombre_in,departamento_in)
            RETURNING id_vendedor INTO rv_id_vendedor;
    end if;
end;
$BODY$;

ALTER PROCEDURE public.almacenar_vendedor(character varying, character varying, integer)
    OWNER TO postgres;
