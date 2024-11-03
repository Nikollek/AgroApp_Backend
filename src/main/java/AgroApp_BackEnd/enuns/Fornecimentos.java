package AgroApp_BackEnd.enuns;

public enum Fornecimentos {

    // Grãos e Cereais
    MILHO(1),
    TRIGO(2),
    ARROZ(3),
    CEVADA(4),
    AVEIA(5),
    SORGO(6),

    // Leguminosas
    SOJA(7),
    FEIJAO(8),
    LENTILHA(9),
    GRAO_DE_BICO(10),
    ERVILHA(11),

    // Frutas
    MACA(12),
    LARANJA(13),
    BANANA(14),
    UVA(15),
    ABACAXI(16),
    MANGA(17),
    MORANGO(18),

    // Hortaliças e Vegetais
    ALFACE(19),
    TOMATE(20),
    CENOURA(21),
    BETERRABA(22),
    BATATA(23),
    ABOBRINHA(24),
    PEPINO(25),

    // Oleaginosas
    GIRASSOL(26),
    AMENDOIM(27),
    LINHACA(28),
    CASTANHA_DO_PARA(29),
    NOZES(30),
    GERGELIM(31),

    // Tubérculos e Rizomas
    BATATA_DOCE(32),
    MANDIOCA(33),
    INHAME(34),
    NABO(35),

    // Plantas Medicinais
    HORTELA(36),
    ALECRIM(37),
    CAMOMILA(38),
    MANJERICAO(39),
    ERVA_CIDREIRA(40),

    // Plantas Industriais
    ALGODAO(41),
    CANA_DE_ACUCAR(42),
    FUMO(43),
    CACAU(44),
    CAFE(45),

    // Árvores Frutíferas
    ABACATEIRO(46),
    MANGUEIRA(47),
    CAJUEIRO(48),
    LIMOEIRO(49),
    FIGUEIRA(50);

    private final int codigo;

    // Construtor do enum
    Fornecimentos(int codigo) {
        this.codigo = codigo;
    }

    // Método para obter o código numérico
    public int getCodigo() {
        return codigo;
    }

    //Método estático para obter o enum pelo código
    public static Fornecimentos fromCodigo(Long codigo){
        for(Fornecimentos fornecimentos : values()){
            if (fornecimentos.getCodigo() == codigo){
                return fornecimentos;
            }
        }
        throw new IllegalArgumentException("Código de fornecimento inválido: " + codigo);
    }
}
