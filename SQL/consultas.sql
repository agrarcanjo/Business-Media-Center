//Consultar ordem de reprodução de mídias a partir do ID terminal

SELECT PP.id_programacao, PP.id_playlist, PP.nr_ordem , MP.nr_ordem, P.nm_playlist, M.id_midia, M.nm_midia 
FROM tb_ppg_playlist_programacao PP 
INNER JOIN tb_pla_playlist P ON P.id_playlist = pp.id_playlist
INNER JOIN tb_mip_midia_playlist MP ON MP.id_playlist = PP.id_playlist
INNER JOIN tb_mid_midia M ON M.id_midia = MP.id_midia
WHERE PP.id_terminal = ?
ORDER BY PP.nr_ordem, MP.nr_ordem
