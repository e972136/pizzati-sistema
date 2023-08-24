-- View: public.v_clientes_saldos

-- DROP VIEW public.v_clientes_saldos;

CREATE OR REPLACE VIEW public.v_clientes_saldos
 AS
 WITH saldos_totales AS (
         SELECT factura.id_cliente AS cliente,
            sum(factura.total_factura) AS total
           FROM factura
          GROUP BY factura.id_cliente
          ORDER BY factura.id_cliente
        )
 SELECT cliente.id_cliente,
    cliente.nombre,
    cliente.ciudad,
    cliente.departamento,
    to_char(saldos_totales.total,'99G999G999D9S') as total
   FROM cliente
     LEFT JOIN saldos_totales ON cliente.id_cliente = saldos_totales.cliente
  ORDER BY cliente.nombre;

ALTER TABLE public.v_clientes_saldos
    OWNER TO postgres;

